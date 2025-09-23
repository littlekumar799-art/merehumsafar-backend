package io.reflectoring.humsafar.security;

import io.reflectoring.humsafar.model.*;
import io.reflectoring.humsafar.repository.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class StaticDataSeeder implements CommandLineRunner {

    @Autowired private EducationTypeRepository educationRepo;
    @Autowired private OccupationTypeRepository occupationRepo;
    @Autowired private EmployedInRepository employedInRepo;
    @Autowired private CasteTypeRepository casteRepo;
    @Autowired private MaritalStatusTypeRepository maritalStatusRepo;
    @Autowired private ProfileForRepository profileForRepo;
    @Autowired private MotherTongueRepository motherTongueRepo;
    @Autowired private  ThemeColorRepository themeColorRepository;
@Autowired private  AppSettingRepository appSettingRepository;
    @Autowired private  BannerRepository  bannerRepository;
    @Override
    public void run(String... args) {
        seedEducationTypes();
        seedOccupationTypes();
        seedCasteTypes();
        seedMaritalStatusTypes();
        seedProfileFor();
        seedEmployedIn();
        seedMotherTongues();
        seedBanner();
        seedThemeColor();
        seedAppSetting();
    }


    private void seedEmployedIn() {
        List<String> employedIn = List.of("Business/Self Employed", "Civil Service", "Defence", "Government/Public Sector", "Private Sector", "Not Working");
        for (String name : employedIn) {
            if (!employedInRepo.existsByName(name)) {
                employedInRepo.save(new EmployedIn(name));
            }
        }
    }
    private void seedEducationTypes() {
        Map<String, String> educationTypes = new HashMap<>();
//        Arts / Science
        educationTypes.put("B.A", "Arts/Science");
        educationTypes.put("B.Ed", "Arts/Science");
        educationTypes.put("B.M.C/B.M.M/B.J.M.C", "Arts/Science");
        educationTypes.put("B.Sc", "Arts/Science");
        educationTypes.put("B.Sc Bachelor Of Fine Arts -BFA/BVA", "Arts/Science");
        educationTypes.put("Bachelor of Library Science", "Arts/Science");
        educationTypes.put("Bachelor Of Physical Education", "Arts/Science");
        educationTypes.put("Bachelor Od Social Work", "Arts/Science");
        educationTypes.put("M.M.C/M.M.M/M.J.M.C", "Arts/Science");
        educationTypes.put("M.Sc", "Arts/Science");
        educationTypes.put("M.Sc(Agriculture)", "Arts/Science");
        educationTypes.put("Master Of Arts - M.A", "Arts/Science");
        educationTypes.put("Master Of Education -M.Ed", "Arts/Science");
        educationTypes.put("Master Of FIne Arts -MFA/MVA", "Arts/Science");
        educationTypes.put("Master Of Library Science", "Arts/Science");
        educationTypes.put("Master Of Physical Education", "Arts/Science");

//Computers
        educationTypes.put("B.C.A", "Computers");
        educationTypes.put("B.IT", "Computers");
        educationTypes.put("M.C.A", "Computers");
        //Doctorate
        educationTypes.put("M.Phil", "Doctorate");
        educationTypes.put("Ph.D", "Doctorate");
        //Engineering /Design
        educationTypes.put("B.Arch", "Engineering/Design");
        educationTypes.put("B.Des./B.D", "Engineering/Design");
        educationTypes.put("B.Pharm/ B.Pharma", "Engineering/Design");
        educationTypes.put("B.tech/B.E", "Engineering/Design");
        educationTypes.put("M.Arch", "Engineering/Design");
        educationTypes.put("M.Des/M.Design", "Engineering/Design");
        educationTypes.put("M.Pharma", "Engineering/Design");
        educationTypes.put("M.S(Engineering)", "Engineering/Design");
        educationTypes.put("M.Tech/M.E", "Engineering/Design");
        //FInance
        educationTypes.put("B.Com", "Finance/Commerce");
        educationTypes.put("CFA", "Finance/Commerce");
        educationTypes.put("Chartered Accountant CA", "Finance/Commerce");
        educationTypes.put("CS", "Finance/Commerce");
        educationTypes.put("ICWA", "Finance/Commerce");
        educationTypes.put("M.Com", "Finance/Commerce");
        //Islamic
        educationTypes.put("Aalim Hafiz/Alaima Hafiz", "Islamic");
        //Law
        educationTypes.put("Bachelor Of Law - L.L.B", "Law");
        educationTypes.put("L.L.M", "Law");
        //Management
        educationTypes.put("BBA", "Management");
        educationTypes.put("BHM", "Management");
        educationTypes.put("M.B.A", "Management");
        //Medicine
        educationTypes.put("B.A.M.S", "Medicine");
        educationTypes.put("B.D.S", "Medicine");
        educationTypes.put("B.H.M.S", "Medicine");
        educationTypes.put("B.P.T", "Medicine");
        educationTypes.put("B.U.M.S", "Medicine");
        educationTypes.put("Bachelor of Nursing", "Medicine");
        educationTypes.put("BVSc.", "Medicine");
        educationTypes.put("D.Pharma", "Medicine");
        educationTypes.put("Doctor Of Medicine -M.D", "Medicine");
        //Medicine
        educationTypes.put("Doctor Of Pharmacy - Pharm. D", "Medicine");
        educationTypes.put("Doctorate Of Medicine - D.M", "Medicine");
        educationTypes.put("M.B.B.S", "Medicine");
        educationTypes.put("M.D.(Homoeopathy)", "Medicine");
        educationTypes.put("M.D.S", "Medicine");
        educationTypes.put("M.P.T", "Medicine");
        educationTypes.put("M.V.Sc.", "Medicine");
        educationTypes.put("Master Of Chiurgiae -M.Ch.", "Medicine");
        educationTypes.put("Master Of Surgery - M.S", "Medicine");
        //Non-Graduate
        educationTypes.put("Diploma", "Non-Graduate");
        educationTypes.put("High School", "Non-Graduate");
        educationTypes.put("Intermediate(12th)", "Non-Graduate");
        educationTypes.put("Trade School", "Non-Graduate");
        //Other
        educationTypes.put("Other", "Non-Graduate");


        for (Map.Entry<String, String> entry : educationTypes.entrySet()) {
            String name = entry.getKey();
            String department = entry.getValue();

            if (!educationRepo.existsByName(name)) {
                educationRepo.save(new EducationType(name, department));
            }
        }
    }

    private void seedOccupationTypes() {
        List<String> occupations = List.of("Looking for Job", "Not Working", "Retired", "Student", "Other");
        for (String name : occupations) {
            if (!occupationRepo.existsByName(name)) {
                occupationRepo.save(new OccupationType(name));
            }
        }
    }

    private void seedCasteTypes() {
        List<String> castes = List.of(
                "Ahmed or Ahmmed",
                "Aarab",
                "Aarzal",
                "Aayanavar",
                "Ansari",
                "Bagban or Baghban",
                "Barbhuiya",
                "Beigh",
                "Bhat",
                "Chhipa",
                "Dar",
                "Deccanis or Deccani",
                "Dudekula",
                "Gada/Gour",
                "Gaddi",
                "Ganai",
                "Idrisi",
                "Kalal(Iraqi)",
                "Khan or Pathan",
                "Konkani",
                "Labbai",
                "Ladaf or Nadaf",
                "Lohar",
                "Lone",
                "Malik",
                "Manihars",
                "Mansoori",
                "Mappila",
                "Memon",
                "Mewati",
                "Mir",
                "Modh Ghanchi",
                "Mughal",
                "Mujawar",
                "Muslim Choudhary",
                "Najars",
                "Parray",
                "Patel",
                "Pinjara",
                "Qazi",
                "Quraishi",
                "Rajput/Chauhan/Thakur",
                "Rangrez",
                "Rather",
                "Rayeen",
                "Reshi",
                "Rowther",
                "Saifi",
                "Salmani",
                "Shah",
                "Shams or Shamsi",
                "Sheikh or Shaikh",
                "Siddique",
                "Sofis",
                "Syed",
                "Tamboli",
                "Tantray",
                "Turq or Turk",
                "Tyagi",
                "Vohra",
                "Wani",
                "No Caste",
                "Other"
        );

        for (String name : castes) {
            if (!casteRepo.existsByName(name)) {
                casteRepo.save(new CasteType(name));
            }
        }
    }

    private void seedMaritalStatusTypes() {
        List<String> statuses = List.of("Never Married", "Married", "Divorced","Separated", "Widowed");
        for (String name : statuses) {
            if (!maritalStatusRepo.existsByName(name)) {
                maritalStatusRepo.save(new MaritalStatusType(name));
            }
        }
    }

    private void seedMotherTongues() {
        List<String> motherTongues = List.of("Hindi", "English", "French", "Spanish", "German", "Italian", "Arabic", "Other");
        for (String name : motherTongues) {
            if (!motherTongueRepo.existsByName(name)) {
                motherTongueRepo.save(new MotherTongue(name));
            }
        }
    }

    private void seedProfileFor() {
        List<String> profiles = List.of("Self", "Brother", "Sister", "Son","Relative/Friend", "Daughter");
        for (String name : profiles) {
            if (!profileForRepo.existsByName(name)) {
                profileForRepo.save(new ProfileFor(name));
            }
        }
    }

    private void seedBanner() {
        List<Banner> banners = Arrays.asList(
                new Banner("Find the One Who Completes Your Soul",
                        "https://i.pinimg.com/736x/e5/5d/58/e55d587b9c1b9621f47b96189761c470.jpg",
                        "Cherish Every Moment With Nikah"),

                new Banner("Verified Profiles. Trusted Matches.",
                        "https://i.pinimg.com/736x/94/38/35/943835f61f02050500d3476ea8522342.jpg",
                        "Start your journey with confidence."),

                new Banner("Love Starts with a Simple Hello",
                        "https://i.pinimg.com/736x/53/ff/fc/53fffcf21625bd46f359fafa2a3bc5eb.jpg",
                        "Your perfect match is just a click away.")
        );

        for (Banner banner : banners) {
            if (!bannerRepository.existsByTitle(banner.getTitle())) {
                bannerRepository.save(banner);
            }
        }
    }


    private void seedThemeColor() {
        // check if already exists (to avoid duplicate seeding)
        if (themeColorRepository.count() == 0) {
            ThemeColor themeColor = new ThemeColor(
                    "Main Theme",
                    "#f7f9fa",
                    "#edf2f4",
                    "#d1d9dc",
                    "#8e9497",
                    "#f7c6cf",
                    "#f18696",
                    "#d90429",
                    "#7a0217",
                    "#ffc9d1",
                    "#ff92a1",
                    "#ff5469",
                    "#992e3e"
            );
            themeColorRepository.save(themeColor);
            System.out.println("🌱 ThemeColor seeded successfully!");
        } else {
            System.out.println("✅ ThemeColor already exists, skipping seeding.");
        }
    }

    //App setting seeder
// App setting seeder
    private void seedAppSetting() {
        // Check agar koi record hai ya nahi
        if (appSettingRepository.count() == 0) {
            ThemeColor defaultTheme = themeColorRepository.findById(1L).orElse(null);

            if (defaultTheme == null) {
                System.out.println("⚠️ Default ThemeColor not found! Please seed ThemeColor first.");
                return;
            }

            AppSetting appSetting = new AppSetting();
            appSetting.setAppName("Humsafar App");
            appSetting.setEmail("merehumsafar@gmail.com");
            appSetting.setInstagram("instagram.com");
            appSetting.setTwitterX("@merehumsafar.twitter.com");
            appSetting.setAbout("mere humsafar  matrimony website");
            appSetting.setContactNumber("7878679876");
            appSetting.setAppLogo("/uploads/logo.png");
            appSetting.setThemeColor(defaultTheme);

            appSettingRepository.save(appSetting);
            System.out.println("🌱 AppSetting seeded successfully with default logo!");
        } else {
            System.out.println("✅ AppSetting already exists, skipping seeding.");
        }
    }

}