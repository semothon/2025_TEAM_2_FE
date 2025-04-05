package com.example.team2.presentation.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.ui.theme.Brown2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "약관 및 정책",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp
                        ),
                        color = Brown2
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로가기",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Brown2
                )
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            PolicyAccordionItem(
                title = "커뮤니티 가이드라인",
                content = "딜리버디 커뮤니티 가이드라인 (초안)\n" +
                        "\n" +
                        "목적\n" +
                        "딜리버디는 안전하고 존중받는 커뮤니티 문화를 만들기 위해 아래와 같은 가이드라인을 운영합니다. 모든 이용자는 아래 가이드라인을 읽고, 이를 준수할 의무가 있습니다.\n" +
                        "\n" +
                        " 우리가 바라는 커뮤니티\n" +
                        "서로를 존중하고 예의를 지켜주세요\n" +
                        "\n" +
                        "약속은 소중합니다, 정확하게 응답하고 제 시간에 도착해주세요\n" +
                        "\n" +
                        "혼밥도 용기지만, 같이 밥 먹는 것도 용기! 열린 마음으로 만남을 즐겨주세요\n" +
                        "\n" +
                        "익명 뒤에 숨지 마세요. 온라인 매너는 오프라인에서도 이어져야 합니다\n" +
                        "\n" +
                        "\n" +
                        "금지되는 행위\n" +
                        "아래와 같은 행위는 엄격히 금지되며, 경고 없이 계정이 정지될 수 있습니다.\n" +
                        "\n" +
                        "욕설, 비방, 혐오 표현\n" +
                        "\n" +
                        "성별, 외모, 지역, 학벌, 종교 등에 대한 차별 표현\n" +
                        "\n" +
                        "거짓 정보 등록\n" +
                        "\n" +
                        "타인의 학교, 학번 도용 / 거짓된 목적의 매칭\n" +
                        "\n" +
                        "노쇼(No-show), 연락두절\n" +
                        "\n" +
                        "약속을 잡아놓고 정당한 사유 없이 나타나지 않거나 연락이 되지 않는 경우\n" +
                        "\n" +
                        "홍보/영업/종교 권유\n" +
                        "\n" +
                        "음료 판매, 투자 권유, 다단계, 종교 등\n" +
                        "\n" +
                        "데이트 목적 또는 성적인 접근\n" +
                        "\n" +
                        "본 서비스는 식사 기반 만남을 위한 서비스이며, 연애/성적 목적의 접근은 금지됩니다\n" +
                        "\n" +
                        "불쾌감 유발 콘텐츠\n" +
                        "\n" +
                        "부적절한 프로필 사진, 방 제목, 소개글 등\n" +
                        "\n" +
                        "위반 시 제재 기준\n" +
                        "위반 횟수\t조치 내용\n" +
                        "1회\t경고 및 일시적 제재\n" +
                        "2회\t계정 일시 정지\n" +
                        "3회 이상 또는 중대한 위반\t영구 정지 및 재가입 제한\n" +
                        "※ 심각한 위반(예: 범죄 혐의, 악의적 사기 등)은 즉시 영구 정지 및 법적 조치가 진행될 수 있습니다.\n" +
                        "\n" +
                        "신고 및 문의\n" +
                        "이용 중 부적절한 행동을 경험했다면 즉시 신고해주세요.\n" +
                        "\n" +
                        "앱 내 신고 기능을 통해 간편하게 신고할 수 있습니다\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "부칙\n" +
                        "본 커뮤니티 가이드라인은 2025년 4월 5일부터 적용됩니다\n" +
                        "\n" +
                        "변경사항 발생 시 앱 내 공지를 통해 사전 안내드립니다"
            )
            PolicyAccordionItem(
                title = "이용약관",
                content = "딜리버디 이용약관 \n" +
                        "\n" +
                        "제1조 (목적)\n" +
                        "이 약관은 딜리버디(이하 \"회사\"라 함)이 제공하는 모바일 애플리케이션 및 관련 서비스(이하 \"서비스\"라 함)의 이용과 관련하여, 회사와 이용자 간의 권리·의무 및 책임사항 등을 규정함을 목적으로 합니다.\n" +
                        "\n" +
                        "제2조 (정의)\n" +
                        "\"서비스\"란, 사용자가 위치 기반 정보를 활용하여 식사할 친구를 찾고, 채팅 및 만남을 주선할 수 있도록 회사가 제공하는 모바일 애플리케이션 및 관련 부가 서비스를 의미합니다.\n" +
                        "\n" +
                        "\"이용자\"란, 본 약관에 따라 회사가 제공하는 서비스를 이용하는 회원 및 비회원을 말합니다.\n" +
                        "\n" +
                        "\"회원\"이란, 회사와 서비스 이용계약을 체결하고 이용자 ID를 부여받은 자를 의미합니다.\n" +
                        "\n" +
                        "\n" +
                        "제3조 (약관의 효력 및 변경)\n" +
                        "본 약관은 서비스를 이용하고자 하는 모든 이용자에게 그 내용을 공지하고 이에 동의함으로써 효력을 발생합니다.\n" +
                        "\n" +
                        "회사는 필요 시 관련 법령을 위반하지 않는 범위에서 약관을 변경할 수 있으며, 변경 시 앱 내 또는 웹사이트에 사전 공지합니다.\n" +
                        "\n" +
                        "\n" +
                        "제4조 (회원가입 및 관리)\n" +
                        "회원가입은 본인의 휴대전화 번호 또는 이메일, SNS 계정 등을 통해 인증한 후, 회사가 정한 방식에 따라 진행됩니다.\n" +
                        "\n" +
                        "회사는 다음 각 호에 해당하는 경우 가입을 제한하거나 취소할 수 있습니다.\n" +
                        "\n" +
                        "실명이 아닌 정보로 가입한 경우\n" +
                        "\n" +
                        "타인의 정보를 도용한 경우\n" +
                        "\n" +
                        "사회 질서를 해치는 목적으로 서비스 이용 신청을 한 경우제\n" +
                        "\n" +
                        "5조 (서비스의 이용)\n" +
                        "본 서비스는 위치 기반으로 주변의 다른 회원을 탐색하고, 친구 요청 및 채팅을 통해 식사 친구를 매칭하는 기능을 제공합니다.\n" +
                        "\n" +
                        "서비스 이용은 연중무휴 24시간 제공을 원칙으로 하나, 시스템 점검, 고장, 기타 회사 사정에 따라 일시적으로 중단될 수 있습니다.\n" +
                        "\n" +
                        "\n" +
                        "제6조 (이용자의 의무)\n" +
                        "이용자는 다음 행위를 하여서는 안 됩니다.\n" +
                        "\n" +
                        "타인의 개인정보 도용 또는 사칭 행위\n" +
                        "\n" +
                        "음란, 혐오, 폭력적 표현 등 부적절한 콘텐츠 등록\n" +
                        "\n" +
                        "상업적 목적의 광고, 홍보, 스팸 전송\n" +
                        "\n" +
                        "회사의 시스템을 해킹하거나 서비스 운영을 방해하는 행위\n" +
                        "\n" +
                        "제7조 (위치정보의 수집 및 이용)\n" +
                        "회사는 회원 간 매칭 및 기능 제공을 위해 단말기의 위치정보를 수집·이용합니다.\n" +
                        "\n" +
                        "위치정보는 매칭에 필요한 범위 내에서만 수집되며, 별도의 동의를 통해 처리됩니다.\n" +
                        "\n" +
                        "회원은 위치정보 제공을 거부할 수 있으나, 이 경우 일부 서비스 이용이 제한될 수 있습니다.\n" +
                        "\n" +
                        "제8조 (지적재산권)\n" +
                        "1. 회사가 제공하는 서비스 및 그에 포함된 콘텐츠(디자인, 이미지, 로고 등)에 대한 저작권 및 지적재산권은 회사에 귀속됩니다.\n" +
                        "2. 이용자가 방 개설 또는 서비스 이용 과정에서 입력한 짧은 텍스트(예: 방 이름, 소개 문구 등)는 서비스 제공 및 운영을 위한 범위 내에서 사용될 수 있습니다.\n" +
                        "3. 이용자는 위 항의 내용을 제외하고, 서비스를 통해 제공되는 정보를 회사의 사전 동의 없이 복제, 전송, 출판, 배포, 방송 등 상업적으로 이용하거나 제3자에게 이용하게 해서는 안 됩니다.\n" +
                        "\n" +
                        "제9조 (책임 제한)\n" +
                        "회사는 회원 간의 만남, 대화, 활동에 대해 개입하지 않으며, 그로 인해 발생한 분쟁에 대해서는 책임을 지지 않습니다.\n" +
                        "\n" +
                        "회사는 무료로 제공되는 서비스에 대해서는 특별한 사정이 없는 한 손해에 대한 책임을 부담하지 않습니다.\n" +
                        "\n" +
                        "제10조 (준거법 및 재판관할)\n" +
                        "본 약관은 대한민국 법령에 따라 해석되며,\n" +
                        "\n" +
                        "서비스 이용 중 발생한 분쟁에 대해서는 회사의 본사 소재지를 관할하는 법원을 제1심 관할 법원으로 합니다.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n"
            )
            PolicyAccordionItem(
                title = "개인정보 처리방침",
                content = "딜리버디 개인정보처리방침\n" +
                        "\n" +
                        "제1조 (총칙)\n" +
                        "딜리버디(이하 \"회사\"라 함)는 이용자의 개인정보를 중요시하며, 「개인정보 보호법」 및 관련 법령을 준수합니다. 본 개인정보처리방침은 회사가 어떤 정보를 수집하고, 어떻게 이용·보관·파기하는지를 설명합니다.\n" +
                        "\n" +
                        "\n" +
                        "제2조 (수집하는 개인정보 항목)\n" +
                        "구분\t수집 항목\t수집 목적\n" +
                        "필수\t전화번호, 닉네임, 성별, 학교, 학번\t회원 식별 및 인증, 학교 기반 친구 매칭\n" +
                        "선택\t방 제목, 소개 문구 등 사용자가 입력한 간단한 텍스트\t방 개설 및 참여 목적 공유\n" +
                        "\n" +
                        "\n" +
                        "제3조 (개인정보 수집 방법)\n" +
                        "앱 회원가입 시 이용자가 직접 입력\n" +
                        "\n" +
                        "학교/학번은 학생 인증을 위한 목적으로 입력받음\n" +
                        "\n" +
                        "\n" +
                        "제4조 (개인정보 이용 목적)\n" +
                        "회사는 수집한 개인정보를 다음과 같은 목적에만 사용합니다.\n" +
                        "\n" +
                        "회원가입 및 본인 확인\n" +
                        "\n" +
                        "학교 기반 친구 매칭 및 방 탐색 기능 제공\n" +
                        "\n" +
                        "앱 서비스 운영, 통계 분석, 고객 응대\n" +
                        "\n" +
                        "\n" +
                        "제5조 (개인정보 보유 및 이용기간)\n" +
                        "회사는 원칙적으로 개인정보 수집 및 이용 목적이 달성되면 해당 정보를 지체 없이 파기합니다.\n" +
                        "\n" +
                        "단, 다음의 경우에는 일정 기간 보존합니다:\n" +
                        "\n" +
                        "항목\t보존 근거\t보존 기간\n" +
                        "전화번호, 학교, 학번 등 기본 회원 정보\t부정 가입 방지, 분쟁 대응\t회원 탈퇴 후 7일\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "제6조 (개인정보의 제3자 제공)\n" +
                        "회사는 이용자의 개인정보를 제3자에게 제공하지 않습니다. 단, 법령에 따른 요청 또는 이용자의 별도 동의가 있는 경우는 예외로 합니다.\n" +
                        "\n" +
                        "\n" +
                        "제7조 (개인정보 처리 위탁)\n" +
                        "회사는 개인정보 처리를 외부에 위탁하지 않으며, 위탁이 필요한 경우 사전 고지 후 동의를 받습니다.\n" +
                        "\n" +
                        "\n" +
                        "제8조 (이용자의 권리와 행사 방법)\n" +
                        "이용자는 언제든지 자신의 개인정보를 조회하거나 수정할 수 있으며, 회원 탈퇴를 통해 삭제를 요청할 수 있습니다.\n" +
                        "\n" +
                        "개인정보 열람·정정·삭제는 앱 내 설정 또는 고객센터를 통해 가능합니다.\n" +
                        "\n" +
                        "\n" +
                        "제9조 (개인정보의 파기 절차 및 방법)\n" +
                        "전자적 파일: 복구할 수 없는 기술적 방법으로 즉시 삭제\n" +
                        "\n" +
                        "서면 등 기록물: 분쇄 또는 소각 방식으로 안전하게 파기\n" +
                        "\n" +
                        "\n" +
                        "제10조 (개인정보 보호를 위한 기술적·관리적 대책)\n" +
                        "비밀번호 및 중요 정보 암호화 저장\n" +
                        "\n" +
                        "접근권한 최소화 및 접근 기록 관리\n" +
                        "\n" +
                        "보안 솔루션을 통한 외부 침입 방지\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "제11조 (개정 및 고지)\n" +
                        "본 방침은 2025년 4월 5일부터 적용되며, 변경 시 앱 내 공지사항 또는 팝업을 통해 사전 안내합니다.\n"
            )
        }
    }
}


@Composable
fun PolicyAccordionItem(title: String, content: String) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Brown2
            )
            Icon(
                imageVector = if (expanded)
                    Icons.Filled.KeyboardArrowDown
                else
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Black
            )
        }

        if (expanded) {
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp) // 최대 높이 제한
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PolicyScreenPreview() {
    val navController = rememberNavController()
    PolicyScreen(navController = navController)
}
