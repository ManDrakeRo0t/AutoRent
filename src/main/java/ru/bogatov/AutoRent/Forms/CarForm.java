package ru.bogatov.AutoRent.Forms;

import lombok.Data;
import ru.bogatov.AutoRent.Entities.CarClass;

@Data
public class CarForm {
    public String mark;
    public String model;
    public Integer price;
    public Integer places;
    public String gearbox;
    public String fuel;
    public String description;
    public String carClass;

}
