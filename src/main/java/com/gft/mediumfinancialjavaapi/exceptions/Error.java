/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gft.mediumfinancialjavaapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author lps10
 */
@AllArgsConstructor
@Data
public class Error {

    private String user_msg;
    private String dev_msg;

}
