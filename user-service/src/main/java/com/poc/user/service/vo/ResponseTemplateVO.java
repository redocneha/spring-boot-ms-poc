package com.poc.user.service.vo;

import com.poc.user.service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {
   private Department department;
   private User user;
   private String error;
}
