DROP TABLE IF EXISTS service.sv_conf;

CREATE TABLE service.sv_conf (
    attr_name VARCHAR(255) NOT NULL,
    attr_value VARCHAR(500) NOT NULL,
    attr_desc VARCHAR(100) NOT NULL DEFAULT '-',
    PRIMARY KEY (attr_name)
)