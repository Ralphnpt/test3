package com.example.test.web;

import com.example.test.entities.Customer;
import com.example.test.respository.CustomerRespository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor
public class CustomerController {
    @Autowired
    private CustomerRespository customerRespository;
    static int num =0;

    @GetMapping(path = "/index")
    public String customers(Model model, @RequestParam(name="keyword",defaultValue = "")
    String keyword) {
        List<Customer> customers;
        if (keyword.isEmpty()) {
            customers = customerRespository.findAll();
        } else {
            long key = Long.parseLong(keyword);
            customers = customerRespository.findCustomerById(key);
        }
        model.addAttribute("listCustomers", customers);
        return "customers";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        customerRespository.deleteById(id);
        return "redirect:/index";
    }
    @GetMapping("/formCutomers")
    public String formCustomers(Model model){
        model.addAttribute("customer", new Customer()); return "formCustomers"; }

    @PostMapping(path="/save")
    public String save(Model model, Customer customer, BindingResult bindingResult, ModelMap mm, HttpSession session)
    {
        if (bindingResult.hasErrors()) { return "formCustomers";
        } else {
            customerRespository.save(customer);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";
        }
    }
    @GetMapping("/editCustomers")
    public String editCustomers(Model model, Long id, HttpSession session){
        num = 2;
        session.setAttribute("info", 0);
        Customer customer = customerRespository.findById(id).orElse(null); if(customer==null) throw new RuntimeException("Customer does not exist"); model.addAttribute("customer", customer);
        return "editStudents";
    }

    @GetMapping(path = "/")
    public String customer2(Model model, ModelMap mm, @RequestParam(name="keyword",defaultValue = "") String keyword,HttpSession session){
        List<Customer> customers; if (keyword.isEmpty()) {
            customers = customerRespository.findAll();
        } else {
            mm.put("e", 0);
            mm.put("a", 0);
            long key = Long.parseLong(keyword);
            customers = customerRespository.findCustomerById(key);
        }
        model.addAttribute("listCustomers", customers);
        return "customers";
    }


}
