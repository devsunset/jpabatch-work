package com.example.jpabatch.sample.controller;

import com.example.jpabatch.sample.entity.Address;
import com.example.jpabatch.sample.entity.Member;
import com.example.jpabatch.sample.service.ItemService;
import com.example.jpabatch.sample.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: HolyEyE
 * Date: 2013. 12. 4. Time: 오후 9:07
 */
@Controller
public class MemberController {

    @Autowired MemberService memberService;
    @Autowired ItemService itemService;

    @RequestMapping(value = "/members/new", method = RequestMethod.GET)
    public String createForm() {
        return "members/createMemberForm";
    }

    @RequestMapping(value = "/members/new", method = RequestMethod.POST)
    public String create(Member member, String city, String street, String zipcode) {
        Address address = new Address(city, street, zipcode);
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
