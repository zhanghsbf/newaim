SELECT
    id,
    gender,
    {{ age_in_years('birth_date') }} AS age,
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
GROUP BY
    1,2,3
