package ru.bogatov.AutoRent.Forms;

import lombok.Data;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.User;

@Data
public class OrderForm {
    public String punct;
    public String car;
    public String date_from;
    public String time_from;
    public String date_to;
    public String time_to;
    public String user;
}
