package com.example.backend.admin.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostAdminSignupRes {
    private String adminEmail;
    private Long idx;
}
