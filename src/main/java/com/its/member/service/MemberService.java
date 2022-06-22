package com.its.member.service;

import com.its.member.dto.MemberDTO;
import com.its.member.entity.MemberEntity;
import com.its.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
       MemberEntity memberEntity = MemberEntity.toEntity(memberDTO); // DTO 를 entitiy 로변환
       Long id = memberRepository.save(memberEntity).getId();
       // Get id 를 했기때문에 id 를 리턴할수가있다.
       return id;
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /**
         * login.html에서 이메일 , 비밀번호를 받아오고
         * DB로 부터 해당 이메일의 정보를 가져와서
         * 입력받은 비번과 DB에서 조회한 비번의일치 여부를 판단하여
         * 일치하면 로그인 성공, 일치하지않으면 로그인 실패로 처리
         */
        Optional<MemberEntity> optionalMemberEntity =memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(optionalMemberEntity.isPresent()){
            MemberEntity loginEntity = optionalMemberEntity.get();
            if(loginEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                return MemberDTO.toDTO(loginEntity);
            }else{
                return null; // 조회는 됐는데 비밀번호가 틀릴때
            }
        }else{
            return null; // 해당계정이 조회가안되었을때
        }
    }

    public MemberDTO findById(Long id){
       Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
       if(optionalMemberEntity.isPresent()){
//           return MemberDTO.toDTO(optionalMemberEntity.get());
           MemberEntity memberEntity = optionalMemberEntity.get();
           MemberDTO memberDTO = MemberDTO.toDTO(memberEntity);
           return memberDTO;
       }else{
           return null;
       }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity member:memberEntityList){
//            MemberDTO memberDTO = MemberDTO.toDTO(member);
//            memberDTOList.add(memberDTO);

            memberDTOList.add(MemberDTO.toDTO(member));

        }
        return memberDTOList;
    }
}
