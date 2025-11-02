ALTER TABLE employees
ADD COLUMN IF NOT EXISTS login VARCHAR(255),
ADD COLUMN IF NOT EXISTS password VARCHAR(255),
ADD COLUMN IF NOT EXISTS created_at TIMESTAMP DEFAULT NOW();

UPDATE employees
SET login = 'employee_' || id::text
WHERE login IS NULL;

UPDATE employees
SET password = 'default_password'
WHERE password IS NULL;

UPDATE employees
SET created_at = NOW()
WHERE created_at IS NULL;

ALTER TABLE employees
ALTER COLUMN login SET NOT NULL,
ALTER COLUMN password SET NOT NULL,
ALTER COLUMN created_at SET NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS employees_login_unique ON employees(login);

