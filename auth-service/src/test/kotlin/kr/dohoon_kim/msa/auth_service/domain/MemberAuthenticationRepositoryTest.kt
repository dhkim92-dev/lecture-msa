package kr.dohoon_kim.msa.auth_service.domain

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.slf4j.LoggerFactory
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@DataJpaTest
class MemberAuthenticationRepositoryTest(
    private val memberAuthenticationRepository: MemberAuthenticationInfoRepository
): AnnotationSpec() {

    override fun extensions() = listOf(SpringExtension)

    private lateinit var memberId: MemberId

    private val logger = LoggerFactory.getLogger(javaClass)

    @BeforeEach
    fun setUp() {
        memberId = MemberId(UUID.randomUUID())
        val defaultMember = MemberAuthenticationInfo(
            id = memberId,
            nickname = "test",
            password = "test"
        )
        memberAuthenticationRepository.save(defaultMember)
        logger.info("default Member : ${defaultMember.identifier}")
    }

    @Test
    fun `사용자 아이디를 통한 조회`() {
        val member = memberAuthenticationRepository.findByIdOrNull(memberId)
        member shouldNotBe null
        member as MemberAuthenticationInfo
        member.identifier shouldBe memberId
        member.nickname shouldBe "test"
        logger.debug("password : ${member.password}")
        member.password shouldBe "test"
    }

    @Test
    fun `인증 정보 엔티티 추가 테스트`() {
        val newMember = MemberAuthenticationInfo(
            id = MemberId(UUID.randomUUID()),
            nickname = "new",
            password = "new"
        )

        val savedMember = memberAuthenticationRepository.save(newMember)
        savedMember shouldNotBe null
        savedMember.identifier shouldBe newMember.identifier
        savedMember.nickname shouldBe newMember.nickname
        savedMember.password shouldBe newMember.password
    }

    @Test
    fun `인증 정보 엔티티 수정 테스트`() {
        val member = memberAuthenticationRepository.findByIdOrNull(memberId)
        member shouldNotBe null
        member as MemberAuthenticationInfo
        member.changeNickname("new")
        member.changePassword("new")
        val updatedMember = memberAuthenticationRepository.save(member)
        updatedMember shouldNotBe null
        updatedMember.identifier shouldBe memberId
        updatedMember.nickname shouldBe "new"
        updatedMember.password shouldBe "new"
    }

    @AfterEach
    fun tearDown() {
        memberAuthenticationRepository.deleteAll()
    }
}