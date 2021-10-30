package java8new._02stream.with.optional;

import java.util.Optional;

public class MobileTesterWithOptional {
	
	public static void main(String[] args) {
		ScreenResolution resolution = new ScreenResolution(750,1334);
		//Null을 허용하지 않는 Optional.of()
		DisplayFeatures dfeatures = new DisplayFeatures("4.7", Optional.of(resolution));
		Mobile mobile = new Mobile(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));
		
		MobileService mService = new MobileService();
		int width = mService.getMobileScreenWidth(Optional.of(mobile));
		System.out.println("Apple iPhone 6s Screen Width = " + width);

		//Not-Null 인 경우
		//Optional의 ifPresent(), isPresent() 사용
		Optional<ScreenResolution> resolutionOptional = dfeatures.getResolution();
		resolutionOptional.ifPresent(System.out::println);
		System.out.println(resolutionOptional.isPresent());

		//Null을 허용하는 Optional.empty()
		Mobile mobile2 = new Mobile(2015001, "Apple", "iPhone 6s", Optional.empty());		
		int width2 = mService.getMobileScreenWidth(Optional.of(mobile2));
		System.out.println("Apple iPhone 16s Screen Width = " + width2);

		//Null 인 경우
		//Optional의 ifPresent(), isPresent() 사용
		Optional<DisplayFeatures> featuresOptional = mobile2.getDisplayFeatures();
		featuresOptional.ifPresent(System.out::println);
		System.out.println(featuresOptional.isPresent());

		//Optional에 담겨진 DisplayFeatures 객체가
		// Null 이 아니면 해당 객체를 반환하고, Null 이면  orElseGet() 메서드의 아규먼트에서 생성한 객체를 반환한다.
		DisplayFeatures features =
				featuresOptional.orElseGet(() -> new DisplayFeatures("0.0", Optional.of(new ScreenResolution(0,0)) ));
		System.out.println(features.getSize());
	}

}
