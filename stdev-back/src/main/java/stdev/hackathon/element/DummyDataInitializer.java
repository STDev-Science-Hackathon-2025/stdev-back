package stdev.hackathon.element;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stdev.hackathon.element.entity.Element;
import stdev.hackathon.element.repository.ElementRepository;
import stdev.hackathon.session.entity.Session;
import stdev.hackathon.session.repository.SessionRepository;
import stdev.hackathon.tip.entity.Tip;
import stdev.hackathon.tip.repository.TipRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyDataInitializer implements CommandLineRunner {

    private final ElementRepository elementRepository;
    private final TipRepository tipRepository;
    private final SessionRepository sessionRepository;

    @Override
    public void run(String... args) {
        if (elementRepository.count() == 0) { // 중복 방지
            elementRepository.saveAll(List.of(
                    Element.builder()
                            .elementName("수소 (H)")
                            .elementDescription("안녕! 나는 주기율표 1번 수소야!\n세상에서 제일 작고 가벼운 원소지!\n내가 없으며 물도 없고, 별도 빛나지 않아.")
                            .elementCharacteristics("우주에서 가장 가볍고 작은 원소야.\n수소차나 연료전지에서 깨끗한 에너지원으로 활약하고 있어.\n공기 중에서 쉽게 다른 원소와 결합하고, 폭발적인 반응을 일으킬 수 있어.\n태양을 비롯한 별들도 나로 인해 빛나고 있어.")
                            .elementLife("1. 수소차\n 수소 연료전지차는 수소와 산소를 반응시켜 전기를 만들어요.\n 배기가스 대신 물만 나와요! 친환경적이죠.\n\n2. 물 속의 수소\n우리가 마시는 물(H₂O) 안에도 수소가 있어요!\n H₂O는 수소(H) 2개와 산소(O) 1개로 되어 있어요.\n\n 3. 우주의 주인공\n우주에 있는 원소 중에서 수소가 가장 많아요. 별도 수소로 만들어지고, 태양도 수소를 핵융합해 빛을 내요!")
                            .elementUrl1("https://youtu.be/RJ-4J3nPqTQ?si=q7RK5sHywiQCy_gg")
                            .elementUrl2(" https://www.youtube.com/watch?v=IgLiVV1Sf7M&pp=ygUn7IiY7IaMIOuwnOqyrCDsi6Ttl5gm7IiY7IaM7Jew66OM7KCE7KeA")
                            .build(),
                    Element.builder()
                            .elementName("탄소 (C)")
                            .elementDescription("하이! 주기율표 6번, 탄소야~\n연필심부터 다이아몬드까지, 나의 변신은 무한해!\n 나 없이는 생명도, 피자도, 스마트폰도 없다고!")
                            .elementCharacteristics("생명체를 이루는 기본 원소로, 사람 몸의 18%가 바로 나야!\n\n 지구에서 네 번째로 많은 원소지!\n\n숯, 흑연, 다이아몬드처럼 여러 모습으로 변신할 수 있어.\n\n 에너지원으로도 활약해! 석탄, 석유, 천연가스에 내가 들어 있어.\n")
                            .elementLife("1. 연필심\n연필심은 흑연으로 만들어졌는데, 흑연도 탄소로 이루어져 있어요!\n\n2. 반짝반짝 다이아몬드\n세상에서 가장 단단한 보석인 다이아몬드도 사실은 탄소예요! 원자 배열이 다를 뿐이죠\n\n3. 음식 속 탄소\n고기, 채소, 빵, 과자 등 우리가 먹는 음식에는 모두 탄소가 들어 있어요. 생명체를 이루는 기본 원소니까요!")
                            .elementUrl1("https://www.youtube.com/watch?v=Up5mZ3sKGlc&pp=ygUN7YOE7IaMIOyLpO2XmA%3D%3D")
                            .elementUrl2("https://www.youtube.com/watch?v=9xHMFT2EFIM&pp=ygUN7YOE7IaMIOyLpO2XmA%3D%3D")
                            .build()
            ));
            System.out.println("✅ Element 더미 데이터 초기화 완료");
        }
        if (tipRepository.count() == 0) {
            tipRepository.saveAll(List.of(
                    Tip.builder()
                            .tipTitle("질소는 왜 비료에 쓰일까?")
                            .tipUrl("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB1qQB7j.img?w=768&h=431&m=6")
                            .tipDescription("식물은 성장할 때 단백질과 엽록소가 있어야 되는데, 질소가 이것을 만드는데 필요합니다. 질소가 충분하면 잎이 무성해지고 광합성이 활발해져 잘 자란답니다.")
                            .build(),
                    Tip.builder()
                            .tipTitle("C(탄소)와 O(산소)가 합쳐지면?")
                            .tipUrl("https://cdn.dtnews24.com/news/photo/201808/523071_155937_250.png")
                            .tipDescription("나무는 CO₂를 C와 O₂로 분리시켜줘요. C하고 O가 합쳐지면 뭐가 될까요?")
                            .build()
            ));
            System.out.println("✅ Tip 더미 데이터 초기화 완료");
        }
        if (sessionRepository.count() == 0) {
            sessionRepository.saveAll(List.of(
                    Session.builder()
                            .identity("질소")
                            .score(45)
                            .build(),
                    Session.builder()
                            .identity("헬륨")
                            .score(70)
                            .build(),
                    Session.builder()
                            .identity("산소")
                            .score(60)
                            .build()
            ));
            System.out.println("✅ Session 더미 데이터 초기화 완료");
        }
    }
}
