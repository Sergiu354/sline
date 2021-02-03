package com.sline.sline.session;

import com.sline.sline.entity.project.company.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
@Data
public class DataSession {
    private Long companyId;
}
