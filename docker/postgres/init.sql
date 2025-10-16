CREATE TABLE tb_code (
    code_type VARCHAR NOT NULL,
    code VARCHAR NOT NULL,
    value VARCHAR NOT NULL,
    active VARCHAR NULL,
    created_at TIMESTAMPTZ NULL,
    updated_at TIMESTAMPTZ NULL,
    CONSTRAINT tb_code_pk PRIMARY KEY (code_type, code)
);
CREATE INDEX tb_code_idx_updated_at ON tb_code USING btree (updated_at);

CREATE TABLE tb_customer (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    birthday VARCHAR NULL,
    gender VARCHAR NULL,
    address VARCHAR NULL,
    phone_number VARCHAR NULL,
    type VARCHAR NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT tb_customer_pk PRIMARY KEY (id)
);
CREATE INDEX tb_customer_idx_name ON tb_customer USING btree (name);

CREATE TABLE tb_dept (
    id VARCHAR NOT NULL,
    name VARCHAR NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT tb_dept_pk PRIMARY KEY (id)
);
CREATE INDEX tb_dept_idx_updated_at ON tb_dept USING btree (updated_at);

CREATE TABLE tb_service_request (
    id VARCHAR NOT NULL,
    title VARCHAR NULL,
    customer_id VARCHAR NOT NULL,
    type VARCHAR NULL,
    detail VARCHAR NULL,
    status VARCHAR NOT NULL,
    call_agent_id VARCHAR NULL,
    voc_assignee_id VARCHAR NULL,
    voc_assignee_dept_id VARCHAR NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT tb_service_request_pk PRIMARY KEY (id)
);
CREATE INDEX tb_service_request_idx_customer ON tb_service_request USING btree (customer_id);
CREATE INDEX tb_service_request_idx_updated_at ON tb_service_request USING btree (updated_at);

CREATE TABLE tb_user (
    id VARCHAR NOT NULL,
    name VARCHAR NULL,
    phone_number VARCHAR NULL,
    email VARCHAR NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT tb_user_pk PRIMARY KEY (id)
);

INSERT INTO tb_code (code_type, code, value, active, created_at, updated_at)
VALUES
  ('SR_TYPE', 'T1', 'Type 1', 'Y', now(), now()),
  ('SR_TYPE', 'T2', 'Type 2', 'Y', now(), now()),
  ('SR_TYPE', 'T3', 'Type 3', 'Y', now(), now()),
  ('SR_TYPE', 'T4', 'Type 4', 'Y', now(), now()),
  ('SR_TYPE', 'T5', 'Type 5', 'Y', now(), now()),
  ('SR_STATUS', 'S1', 'Open', 'Y', now(), now()),
  ('SR_STATUS', 'S2', 'In Progress', 'Y', now(), now()),
  ('SR_STATUS', 'S3', 'Closed', 'Y', now(), now()),
  ('SR_STATUS', 'S4', 'On Hold', 'Y', now(), now()),
  ('SR_STATUS', 'S5', 'Cancelled', 'Y', now(), now()),
  ('SR_STATUS', 'S6', 'Resolved', 'Y', now(), now());

INSERT INTO tb_dept (id, name, created_at, updated_at)
SELECT 'DEPT' || i, 'Dept ' || i, now(), now()
FROM generate_series(1, 200) AS s(i);

INSERT INTO tb_user (id, name, phone_number, email, created_at, updated_at)
SELECT 'USER' || i,
       'User ' || i,
       '010' || lpad((10000000 + i)::text, 8, '0'),
       'user' || i || '@test.com',
       now(),
       now()
FROM generate_series(1, 500) AS s(i);

INSERT INTO tb_customer (id, name, birthday, gender, address, phone_number, type, created_at, updated_at)
SELECT 'CUST' || i,
       'Customer ' || i,
       '19' || lpad((70 + (i % 30))::text, 2, '0') || '-01-01',
       CASE WHEN i % 2 = 0 THEN 'M' ELSE 'F' END,
       'Address ' || i,
       '010' || lpad((20000000 + i)::text, 8, '0'),
       'TYPE',
       now(),
       now()
FROM generate_series(1, 1000) AS s(i);

INSERT INTO tb_service_request (id, title, customer_id, type, detail, status, call_agent_id, voc_assignee_id, voc_assignee_dept_id, created_at, updated_at)
SELECT 'SR' || i,
       'Service Request ' || i,
       'CUST' || ((i % 1000) + 1),
       'T' || ((i % 5) + 1),
       'Detail for request ' || i,
       'S' || ((i % 6) + 1),
       'USER' || ((i % 500) + 1),
       'USER' || (((i + 1) % 500) + 1),
       'DEPT' || ((i % 200) + 1),
       now(),
       now()
FROM generate_series(1, 10000) AS s(i);
