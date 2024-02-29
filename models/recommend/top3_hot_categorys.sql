select
     category
    ,rank
from (
    select 
         category
        ,row_number() over (partition by 1 order by count(1) desc) as rank
    from {{ ref('transactions') }}
    group by category
) tmp
where rank <= 3
