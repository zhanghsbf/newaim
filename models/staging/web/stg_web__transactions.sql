
with source as (
    select * from {{ source('web','transactions') }}
),

renamed as (
    select
        id,
        contact_id,
        {{ as_timestamp_utc('transaction_date') }} as transaction_date,
        amount,
        item_count,
        category, 
        _loaded_at_utc
    from source    
),

clean_older as (
    select * from renamed 
--    where transaction_date >= (current_date - 1)  过滤超过1天的数据
--    where transaction_date >= (to_date('2023-04-12','yyyy-MM-dd') - 1)  --测试用,假设跑数日期为2023-04-12
--    and transaction_date < to_date('2023-04-12','yyyy-MM-dd')
)

select * from clean_older

