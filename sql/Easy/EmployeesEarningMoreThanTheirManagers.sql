Write a solution to find the employees who earn more than their managers.

Return the result table in any order.

The result format is in the following example.

select e1.name as Employee from Employee e1
    join Employee m on e1.managerId = m.id
    where e1.salary > m.salary;
