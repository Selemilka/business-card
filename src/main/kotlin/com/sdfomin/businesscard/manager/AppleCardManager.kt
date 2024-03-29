package com.sdfomin.businesscard.manager

import com.sdfomin.businesscard.db.service.BusinessCardService
import com.sdfomin.businesscard.entity.BusinessCard
import de.brendamour.jpasskit.PKBarcode
import de.brendamour.jpasskit.PKField
import de.brendamour.jpasskit.PKPass
import de.brendamour.jpasskit.enums.PKBarcodeFormat
import de.brendamour.jpasskit.enums.PKPassType
import de.brendamour.jpasskit.enums.PKTextAlignment
import de.brendamour.jpasskit.passes.PKGenericPass
import de.brendamour.jpasskit.signing.PKFileBasedSigningUtil
import de.brendamour.jpasskit.signing.PKPassTemplateFolder
import de.brendamour.jpasskit.signing.PKSigningInformation
import de.brendamour.jpasskit.signing.PKSigningInformationUtil
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.http.*
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.charset.Charset

@Component
class AppleCardManager(
    val resourceLoader: ResourceLoader,
    private val businessCardService: BusinessCardService,
    @Value("\${businesscard.resources}") val passPath: String,
) {

    fun generate(
        handle: String,
        name: String,
        telegram: String,
        profession: String,
        website: String,
    ): ResponseEntity<Resource> {
        generatePass(handle, name, profession, telegram, website)
        return loadPass(handle)
    }

    fun generate(
        businessCard: BusinessCard,
    ): ResponseEntity<Resource> = generate(
        handle = businessCard.handle,
        name = businessCard.businessContacts.name!!,
        telegram = businessCard.businessContacts.telegram!!,
        profession = businessCard.description,
        website = businessCard.businessContacts.website!!,
    )

    private fun generatePass(handle: String, name: String, profession: String, telegram: String, website: String) {
        val pass = PKPass.builder()
            .pass(
                PKGenericPass.builder()
                    .passType(PKPassType.PKGenericPass)
                    .primaryFieldBuilder(
                        PKField.builder()
                            .key("primary-name")
                            .value(name)
                    )
                    .headerFieldBuilder(
                        PKField.builder()
                            .key("header-profession")
                            .textAlignment(PKTextAlignment.PKTextAlignmentRight)
                            .label("О КОМПАНИИ")
                            .value(profession)
                    )
                    .secondaryFieldBuilder(
                        PKField.builder()
                            .key("TELEGRAM")
                            .textAlignment(PKTextAlignment.PKTextAlignmentLeft)
                            .label("TELEGRAM")
                            .value("@$telegram")
                    )
                    .auxiliaryFieldBuilder(
                        PKField.builder()
                            .key("ВЕБ-САЙТ")
                            .textAlignment(PKTextAlignment.PKTextAlignmentLeft)
                            .label("WEBSITE")
                            .value(website)
                    )
                    .auxiliaryFieldBuilder(
                        PKField.builder()
                            .key("ТЕЛЕФОН")
                            .textAlignment(PKTextAlignment.PKTextAlignmentLeft)
                            .label("PHONE")
                            .value("+7-800-555-35-35")
                    )
                    .backFieldBuilder(
                        PKField.builder()
                            .key("back-telegram")
                            .label("TELEGRAM")
                            .attributedValue("<a href='https://t.me/$telegram'>@$telegram</a>")
                    )
                    .backFieldBuilder(
                        PKField.builder()
                            .key("back-website")
                            .label("ВЕБ-САЙТ")
                            .attributedValue("<a href='https://$website'>$website</a>")
                    )
                    .backFieldBuilder(
                        PKField.builder()
                            .key("back-phone")
                            .label("НОМЕР ТЕЛЕФОНА")
                            .attributedValue("<a href='tel:+7-800-555-35-35'>+7-800-555-35-35</a>")
                    )
            )
            .barcodeBuilder(
                PKBarcode.builder()
                    .format(PKBarcodeFormat.PKBarcodeFormatQR)
                    .message("card.selemilka.ru/axolotl")
                    .messageEncoding(Charset.forName("utf-8"))
            )
            .formatVersion(1)
            .passTypeIdentifier("pass.com.selemilka.diploma")
            .serialNumber("000000001")
            .teamIdentifier("8LJXAKA3QW")
            .organizationName("Selemilka")
            .description(name)
            .backgroundColor("rgb(202,209,197)")
            .foregroundColor("rgb(77,100,62)")
            .labelColor("rgb(46,60,37)")
            .build()

        val keyStorePath = "$passPath/cert.p12"
        val keyStorePassword = "selemilkaMyBro!"
        val appleWWDRCA = "$passPath/AppleWWDRCA.pem"
        val templatePath = "$passPath/template"

        val pkSigningInformation: PKSigningInformation =
            PKSigningInformationUtil().loadSigningInformationFromPKCS12AndIntermediateCertificate(keyStorePath,
                keyStorePassword,
                appleWWDRCA)
        val passTemplate = PKPassTemplateFolder(templatePath)
        val pkSigningUtil = PKFileBasedSigningUtil()
        val signedAndZippedPkPassArchive: ByteArray =
            pkSigningUtil.createSignedAndZippedPkPassArchive(pass, passTemplate, pkSigningInformation)

        val outputFile = "$passPath/$handle.pkpass"
        val inputStream = ByteArrayInputStream(signedAndZippedPkPassArchive)

        val targetFile = File(outputFile)
        FileUtils.copyInputStreamToFile(inputStream, targetFile)
    }

    private fun loadPass(handle: String): ResponseEntity<Resource> {
        val resource: Resource = resourceLoader.getResource("file:$passPath/$handle.pkpass")
        val mediaType = MediaTypeFactory
            .getMediaType(resource)
            .orElse(MediaType.APPLICATION_OCTET_STREAM)
        val headers = HttpHeaders()
        headers.contentType = mediaType
        val disposition = ContentDisposition
            .inline()
            .filename("$handle.pkpass")
            .build()
        headers.contentDisposition = disposition
        return ResponseEntity<Resource>(resource, headers, HttpStatus.OK)
    }
}
