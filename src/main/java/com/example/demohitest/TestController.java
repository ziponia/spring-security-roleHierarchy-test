package com.example.demohitest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @PreAuthorize("hasAnyRole('대표이사')")
    @GetMapping(value = "/대표이사")
    public String 대표이사() {
        return "대표이사";
    }

    @PreAuthorize("hasAnyRole('개발팀장')")
    @GetMapping(value = "/개발팀장")
    public String 개발팀장() {
        return "개발팀장";
    }

    @PreAuthorize("hasAnyRole('디자인팀장')")
    @GetMapping(value = "/디자인팀장")
    public String 디자인팀장() {
        return "디자인팀장";
    }

    @PreAuthorize("hasAnyRole('회계팀장')")
    @GetMapping(value = "/회계팀장")
    public String 회계팀장() {
        return "회계팀장";
    }

    @PreAuthorize("hasAnyRole('마케팅팀장')")
    @GetMapping(value = "/마케팅팀장")
    public String 마케팅팀장() {
        return "마케팅팀장";
    }

    @PreAuthorize("hasAnyRole('프런트엔드팀')")
    @GetMapping(value = "/프런트엔드팀")
    public String 프런트엔드팀() {
        return "프런트엔드팀";
    }

    @PreAuthorize("hasAnyRole('안드로이드팀')")
    @GetMapping(value = "/안드로이드팀")
    public String 안드로이드팀() {
        return "안드로이드팀";
    }

    @PreAuthorize("hasAnyRole('IOS팀')")
    @GetMapping(value = "/IOS팀")
    public String IOS팀() {
        return "IOS팀";
    }

    @PreAuthorize("hasAnyRole('Web팀')")
    @GetMapping(value = "/Web팀")
    public String Web팀() {
        return "Web팀";
    }

    @PreAuthorize("hasAnyRole('백엔드팀')")
    @GetMapping(value = "/백엔드팀")
    public String 백엔드팀() {
        return "백엔드팀";
    }

    @PreAuthorize("hasAnyRole('결제팀')")
    @GetMapping(value = "/결제팀")
    public String 결제팀() {
        return "결제팀";
    }

    @PreAuthorize("hasAnyRole('주문팀')")
    @GetMapping(value = "/주문팀")
    public String 주문팀() {
        return "주문팀";
    }

    @PreAuthorize("hasAnyRole('상품팀')")
    @GetMapping(value = "/상품팀")
    public String 상품팀() {
        return "상품팀";
    }

    @PreAuthorize("hasAnyRole('인프라관리팀')")
    @GetMapping(value = "/인프라관리팀")
    public String 인프라관리팀() {
        return "인프라관리팀";
    }

    @PreAuthorize("hasAnyRole('뉴스관리팀')")
    @GetMapping(value = "/뉴스관리팀")
    public String 뉴스관리팀() {
        return "뉴스관리팀";
    }

    @PreAuthorize("hasAnyRole('SNS관리팀')")
    @GetMapping(value = "/SNS관리팀")
    public String SNS관리팀() {
        return "SNS관리팀";
    }
}
