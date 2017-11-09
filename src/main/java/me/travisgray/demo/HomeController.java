package me.travisgray.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    //When you create an instance of an object you use ObjectClass.someObject =new ObjectClass() this creates an instnace of the object and you can use it within where it is called.
    //Tells the complier to instiate the repo object when the application runs.
    @Autowired
    AddressbookRepository addressbookRepository;


    @RequestMapping("/")
    public String listAdresses(Model model){
        model.addAttribute("addresses",addressbookRepository.findAll());
        return "addresslist";
    }


    @GetMapping("/add")
    public String addressForm(Model model){
        model.addAttribute("address",new AddressBook());
        return "addressform";
    }


    @PostMapping("/process")
    public String processaddressForm(@Valid AddressBook addressBook, BindingResult result)
    {
        System.out.println(addressBook.toString());
        System.out.println(result.toString());
        if (result.hasErrors()){
            return "addressform";
        }

        addressbookRepository.save(addressBook);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showAddress(@PathVariable("id") long id, Model model){
        model.addAttribute("address",addressbookRepository.findOne(id));
        return "showaddress";
    }

    @RequestMapping("/update/{id}")
    public String updateAddress(@PathVariable("id")long id, Model model){
        model.addAttribute("address",addressbookRepository.findOne(id));
        return "addressform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id")long id){
        addressbookRepository.delete(id);
        return "redirect:/";
    }
}
