--lets get all employees job titles
select first_name, last_name, job_title
from employees e join jobs j on e.job_id = e.job_id;

--inner join
select first_name, last_name, address
from CUSTOMER c join ADDRESS a
on c.address_id = a.ADDRESS_ID;