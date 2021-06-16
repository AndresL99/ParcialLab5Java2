package com.utn.spring.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportDataApi
{
    @SerializedName("player_id")
    private Integer idPlayer;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("age")
    private String age;

    @SerializedName("height")
    private String height;

    @SerializedName("country")
    private CountryApi country;
}
