CREATE TABLE IF NOT EXISTS partner
(
    partner_id serial  NOT NULL,
    login      varchar NOT NULL,
    PRIMARY KEY (partner_id)
);

CREATE TABLE IF NOT EXISTS contact
(
    contacts_id serial  NOT NULL,
    name        varchar NOT NULL,
    website     varchar,
    email       varchar,
    phone       varchar,
    telegram    varchar,
    whatsapp    varchar,
    PRIMARY KEY (contacts_id)
);

CREATE TABLE IF NOT EXISTS business_card
(
    business_card_id     serial  NOT NULL,
    partner_id           integer REFERENCES contact (contacts_id),
    handle               varchar NOT NULL,
    logo_image           varchar,
    description          varchar,
    business_contacts_id integer REFERENCES contact (contacts_id),
    person_contacts_id   integer REFERENCES contact (contacts_id),
    PRIMARY KEY (business_card_id)
);
