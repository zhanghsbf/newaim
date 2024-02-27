with marco_result as (
    select {{ age_in_years("'2023-02-12'") }} as expect_result
)
select
    1
from marco_result
where expect_result <> 1
