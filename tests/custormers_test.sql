select
    1
from
(
    SELECT
        sum(total_expense) as total_expense,
        date(min(first_purchase_date)) as first_purchase_date,
        date(max(last_purchase_date)) as last_purchase_date,
        sum(apps_games_amount) as apps_games_amount,
        sum(beauty_amount)   as beauty_amount,
        sum(books_amount)   as books_amount,
        sum(clothing_shoes_accessories_amount)   as clothing_shoes_accessories_amount,
        sum(movies_tv_amount)   as movies_tv_amount,
        sum(sports_fitness_outdoors_amount)   as sports_fitness_outdoors_amount
    FROM  {{ ref('customers') }}
) t1
join (
    SELECT
        sum(amount) as total_expense,
        date(min(transaction_date)) as first_purchase_date,
        date(max(transaction_date)) as last_purchase_date,
        sum(case when category = 'Apps & Games' then amount else 0 end) as apps_games_amount,
        sum(case when category = 'Beauty' then amount else 0 end)   as beauty_amount,
        sum(case when category = 'Books' then amount else 0 end)   as books_amount,
        sum(case when category = 'Clothing, Shoes & Accessories' then amount else 0 end)   as clothing_shoes_accessories_amount,
        sum(case when category = 'Movies & TV' then amount else 0 end)   as movies_tv_amount,
        sum(case when category = 'Sports, Fitness & Outdoors' then amount else 0 end)   as sports_fitness_outdoors_amount
    FROM  {{ ref('contacts_joined_with_transactions') }}
) t2
on 1 = 1
where (
     nullif(cast(t1.total_expense as decimal(15,1))                   ,0)  <> nullif(cast(t2.total_expense                      as decimal(15,1)) ,0)
  or nullif(t1.first_purchase_date               ,'9999-12-31')       <> nullif(t2.first_purchase_date               ,'9999-12-31')
  or nullif(t1.last_purchase_date               ,'9999-12-31')        <> nullif(t2.last_purchase_date               ,'9999-12-31')
  or nullif(cast(t1.apps_games_amount                 as decimal(15,1)),0) <> nullif(cast(t2.apps_games_amount                 as decimal(15,1)) ,0)
  or nullif(cast(t1.beauty_amount                     as decimal(15,1)),0) <> nullif(cast(t2.beauty_amount                     as decimal(15,1)) ,0)
  or nullif(cast(t1.books_amount                      as decimal(15,1)),0) <> nullif(cast(t2.books_amount                      as decimal(15,1)) ,0)
  or nullif(cast(t1.clothing_shoes_accessories_amount as decimal(15,1)),0) <> nullif(cast(t2.clothing_shoes_accessories_amount as decimal(15,1)) ,0)
  or nullif(cast(t1.movies_tv_amount                  as decimal(15,1)),0) <> nullif(cast(t2.movies_tv_amount                  as decimal(15,1)) ,0)
  or nullif(cast(t1.sports_fitness_outdoors_amount    as decimal(15,1)),0) <> nullif(cast(t2.sports_fitness_outdoors_amount    as decimal(15,1)) ,0)
)
