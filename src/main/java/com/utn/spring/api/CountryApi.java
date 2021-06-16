package com.utn.spring.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryApi
{
    @SerializedName("country_id")
    private Integer countryId;

    @SerializedName("name")
    private String name;

    @SerializedName("country_code")
    private String code;

    @SerializedName("Continent")
    private String continent;
}
