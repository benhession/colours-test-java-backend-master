package com.answerdigital.colourstest.dto;

import com.answerdigital.colourstest.model.Colour;

import java.util.List;

public class AddPersonDTO {
    private String firstname;
    private String lastname;
    private boolean authorised;
    private boolean enabled;
    private List<Colour> colours;

    public AddPersonDTO() {
    }

    public AddPersonDTO(String firstname, String lastname, boolean authorised, boolean enabled, List<Colour> colours) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.authorised = authorised;
        this.enabled = enabled;
        this.colours = colours;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Colour> getColours() {
        return colours;
    }

    public void setColours(List<Colour> colours) {
        this.colours = colours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddPersonDTO that = (AddPersonDTO) o;

        if (isAuthorised() != that.isAuthorised()) return false;
        if (isEnabled() != that.isEnabled()) return false;
        if (!getFirstname().equals(that.getFirstname())) return false;
        if (!getLastname().equals(that.getLastname())) return false;
        return getColours() != null ? getColours().equals(that.getColours()) : that.getColours() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstname().hashCode();
        result = 31 * result + getLastname().hashCode();
        result = 31 * result + (isAuthorised() ? 1 : 0);
        result = 31 * result + (isEnabled() ? 1 : 0);
        result = 31 * result + (getColours() != null ? getColours().hashCode() : 0);
        return result;
    }
}
