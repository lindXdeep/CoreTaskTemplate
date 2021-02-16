package jm.task.core.hiber.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jm.task.core.hiber.model.Car;
import jm.task.core.hiber.service.CarService;
import jm.task.core.hiber.service.UserService;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String cars(ModelMap model, @RequestParam(value = "count", defaultValue = "1000") int count) {

        List<Car> allcars = carService.listCars();

        if (count == 1000) {
            model.addAttribute("cars", carService.listCars());
        }else{
            
            List<Car> cars = new ArrayList<>();

            int i = 0;
            while(i++ < count){
                cars.add(allcars.get(i));
            }

            model.addAttribute("cars", cars);
        }

        return "cars";
    }

    @GetMapping("car/{id}")
    public String car(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("car", carService.getCarbyId(id));

        System.out.println(carService.getCarbyId(id).toString());

        return "/car";
    }

    @GetMapping("/car/add")
    public String createCar(@ModelAttribute("car") Car car) {

        return "create_car";
    }

    @PostMapping("car/add")
    public String create(@ModelAttribute("car") Car car) {

        carService.add(car);

        return "redirect:/user/add";
    }

}
