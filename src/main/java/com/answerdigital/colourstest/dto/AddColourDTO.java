package com.answerdigital.colourstest.dto;

public class AddColourDTO {

    private String name;

    public AddColourDTO() {
    }

    public AddColourDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddColourDTO that = (AddColourDTO) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
