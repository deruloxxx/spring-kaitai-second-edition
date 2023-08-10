package com.example.practicespringboot.app.form;

import jakarta.validation.GroupSequence;

// 左から設定されたインターフェースの順番でバリデーションをしていきます。
@GroupSequence({ValidGroup1.class, ValidGroup2.class})
public interface GroupOrder {
}
