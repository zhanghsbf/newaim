with marco_result as (
    select {{ as_timestamp_utc("'2023-02-27 21:00:00'") }} as expect_result
)
select
    1
from marco_result
where cast(pg_typeof(expect_result) as text) not like '%timestamp%'
