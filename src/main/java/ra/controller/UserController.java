package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.User;
import ra.model.service.IUserService;

//@RestController // bao gồm 2 annotation là : Controller  và ResponseBody của web service
@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping
    public String list(@RequestParam(defaultValue = "") String name,Model model,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size,@RequestParam(defaultValue = "fullName") String sort,@RequestParam(defaultValue = "asc") String by){
        model.addAttribute("list",userService.findAll(name,page,size,sort, by));
        model.addAttribute("name",name);
        return "list";
    }
    @GetMapping("/add")
    public ModelAndView Add(){
        return new ModelAndView("add","user",new User());
    }
    @GetMapping("/edit/{id}")
    public ModelAndView Edit(@PathVariable Long id){
        return new ModelAndView("edit","user",userService.findByID(id));
    }
    @GetMapping("/delete/{id}")
    public String list(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/";
    }
    @PostMapping("/add")
    public String doAdd(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/";
    }
    @PostMapping("/update")
    public String doUpdate(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/";
    }
}
