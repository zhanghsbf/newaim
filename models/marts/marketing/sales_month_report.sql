SELECT
     count(id)                                  as total_count
    ,cast(count(id) / 31 as decimal(24,2))      as day_avg_count
    ,sum(case when category = 'Apps & Games'                    then 1 else 0 end)   as apps_games_count
    ,sum(case when category = 'Beauty'                          then 1 else 0 end)   as beauty_count
    ,sum(case when category = 'Books'                           then 1 else 0 end)   as books_count
    ,sum(case when category = 'Clothing, Shoes & Accessories'   then 1 else 0 end)   as clothing_shoes_accessories_count
    ,sum(case when category = 'Movies & TV'                     then 1 else 0 end)   as movies_tv_count
    ,sum(case when category = 'Sports, Fitness & Outdoors'      then 1 else 0 end)   as sports_fitness_outdoors_count

    ,sum(amount) as total_expense
    ,cast(sum(amount) / 31 as decimal(24,4))    as day_avg_amount
    ,sum(case when category = 'Apps & Games'                    then amount else 0 end)   as apps_games_amount
    ,sum(case when category = 'Beauty'                          then amount else 0 end)   as beauty_amount
    ,sum(case when category = 'Books'                           then amount else 0 end)   as books_amount
    ,sum(case when category = 'Clothing, Shoes & Accessories'   then amount else 0 end)   as clothing_shoes_accessories_amount
    ,sum(case when category = 'Movies & TV'                     then amount else 0 end)   as movies_tv_amount
    ,sum(case when category = 'Sports, Fitness & Outdoors'      then amount else 0 end)   as sports_fitness_outdoors_amount
FROM  {{ ref('stg_web__transactions') }}
where transaction_date between '2023-05-01' and '2023-05-31'  -- 样例，实际脚本可改成传参
