package ru.bogatov.AutoRent.Forms;

import lombok.Data;

@Data
public class EditOrderForm {
    public Integer id;
    public String details;
    public Boolean status;
}
