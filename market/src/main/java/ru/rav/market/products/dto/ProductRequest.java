package ru.rav.market.products.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("DTO for execute update/save request from user")
public class ProductRequest {

    @ApiModelProperty(value = "id of the product, fill it only for update operation")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @ApiModelProperty(value = "name of the product", required = true)
    private String title;

    @Min(value = 0, message = "Enter a correct price")
    @ApiModelProperty(value = "price of the product", required = true)
    private long price;

}
