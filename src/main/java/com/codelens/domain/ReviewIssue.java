package com.codelens.domain;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="review_issue",uniqueConstraints=@UniqueConstraint(columnNames={"task_id","fingerprint"})) public class ReviewIssue { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @ManyToOne(optional=false) @JoinColumn(name="task_id") public ReviewTask task; public String fingerprint,ruleId,severity,title,filePath,evidence,impact,suggestion; public int startLine,endLine; @Column(precision=5,scale=4) public BigDecimal confidence; }
