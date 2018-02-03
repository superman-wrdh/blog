package com.hc.entity;

import com.hc.entity.base.BloggerBase;

/**
 * 博主实体
 * @author hc
 *
 */
public class Blogger extends BloggerBase{
      private String secretKey;

      public String getSecretKey() {
        return secretKey;
      }

     public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
     }
}
