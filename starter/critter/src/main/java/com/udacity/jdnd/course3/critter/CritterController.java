/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:42:44
 * @modify date 2024-06-12 21:42:44
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {

    @GetMapping("/test")
    public String test(){
        return "Critter Starter installed successfully";
    }
}
