package com.its.member.repository;

import com.its.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // select *from member_table where memberEmail = ?
    // 리턴타입: MemberEntity
    // 매개변수: memberEmail(String)
    // 추상메서드: 인터페이스에서 사용 가능한 메서드
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    //Repositoru에 findByMemberEmail 이라는 메서드를 정의하였다.


}
