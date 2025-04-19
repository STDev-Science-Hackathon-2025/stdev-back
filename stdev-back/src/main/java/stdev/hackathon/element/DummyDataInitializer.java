package stdev.hackathon.element;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stdev.hackathon.element.entity.Element;
import stdev.hackathon.element.repository.ElementRepository;
import stdev.hackathon.tip.entity.Tip;
import stdev.hackathon.tip.repository.TipRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyDataInitializer implements CommandLineRunner {

    private final ElementRepository elementRepository;
    private final TipRepository tipRepository;

    @Override
    public void run(String... args) {
        if (elementRepository.count() == 0) { // 중복 방지
            elementRepository.saveAll(List.of(
                    Element.builder()
                            .elementName("수소 (H)")
                            .elementDescription("안녕! 나는 주기율표 1번 수소야!\n세상에서 제일 작고 가벼운 원소지!\n내가 없으며 물도 없고, 별도 빛나지 않아.")
                            .elementCharacteristics("우주에서 가장 가볍고 작은 원소야.\n수소차나 연료전지에서 깨끗한 에너지원으로 활약하고 있어.\n공기 중에서 쉽게 다른 원소와 결합하고, 폭발적인 반응을 일으킬 수 있어.\n태양을 비롯한 별들도 나로 인해 빛나고 있어.")
                            .elementUrl1("https://youtu.be/RJ-4J3nPqTQ?si=q7RK5sHywiQCy_gg")
                            .elementUrl2(" https://www.youtube.com/watch?v=IgLiVV1Sf7M&pp=ygUn7IiY7IaMIOuwnOqyrCDsi6Ttl5gm7IiY7IaM7Jew66OM7KCE7KeA")
                            .build(),
                    Element.builder()
                            .elementName("탄소 (C)")
                            .elementDescription("안녕! 나는 주기율표 6번, 탄소야!\n생명을 이루는 기본 재료지!\n연필심부터 다이아몬드까지, 나 없인 생명도 없어.")
                            .elementCharacteristics("생명을 이루는 가장 중요한 원소 중 하나야.\n다이아몬드처럼 단단하거나, 흑연처럼 부드러운 모습으로도 존재할 수 있어.\n사람의 몸, 나무, 연필심, 심지어 공기 속 이산화탄소에도 내가 들어 있어.\n다양한 화합물을 만들 수 있어서 '화학의 중심'이라고도 불려.")                            .elementUrl1("https://example.com/hydrogen1.jpg")
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
    }
}
