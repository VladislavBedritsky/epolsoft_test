package org.example.epolsofttest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Attribute Data Transfer Object
 *
 * @version 1.01 26 Sep 2020
 * @author Uladzislau Biadrytski
 */
public class AttributeDto {

    @NotBlank
    @Pattern(regexp = "[A-Za-z]+[_-][A-Za-z]+")
    private String name;
    @NotBlank
    private String value;
    @NotBlank
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeDto that = (AttributeDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, description);
    }

    @Override
    public String toString() {
        return "AttributeDto{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
