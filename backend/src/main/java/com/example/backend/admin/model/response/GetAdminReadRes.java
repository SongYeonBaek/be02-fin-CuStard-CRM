package com.example.backend.admin.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAdminReadRes {
    private Long idx;
    private String adminEmail;
}
