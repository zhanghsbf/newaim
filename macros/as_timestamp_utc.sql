{% macro as_timestamp_utc(column_name) %}
    {%- if target.profile_name == 'integration_tests' %}
        to_timestamp({{ column_name }},'YYYY-MM-DD HH24:MI:SS.MS') :: timestamp at TIME zone 'UTC'
    {% endif -%}
    {%- if target.profile_name == 'env_databricks' %}
        to_timestamp({{ column_name }})
    {% endif -%}
{% endmacro %}
