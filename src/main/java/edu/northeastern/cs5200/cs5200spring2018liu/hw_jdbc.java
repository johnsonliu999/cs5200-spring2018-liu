package edu.northeastern.cs5200.cs5200spring2018liu;

import edu.northeastern.cs5200.cs5200spring2018liu.daos.*;
import edu.northeastern.cs5200.cs5200spring2018liu.models.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;

public class hw_jdbc {

    // developers
    private static final Developer alice, bob, charlie, dan, ed;

    // websites
    private static final Website facebook, twitter, wiki, cnn, cnet, gizmodo;

    // pages
    private static final Page home, about, contact, preferences, profile;

    // widgets
    private static final Widget head123, post, head345, intro, image, video;

    // roles
    private static final Role owner, admin, writer, editor, reviewer;

    // priviledges
    private static final Priviledge create, read, update, delete;

    static {
        // date
        Date gradeDate = new Date(System.currentTimeMillis());

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DATE, 24);

        Date dueDate = new Date(cal.getTimeInMillis());

        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DATE, 7);

        Date semesterDate = new Date(cal.getTimeInMillis());

        // developers
        alice = new Developer(12, "Alice", "Wonder", "alice",
                "alice", "alice@wonder.com", null, null, null,
                "4321rewq", null);
        bob = new Developer(23, "Bob", "Marley", "bob",
                "bob", "bob@marley.com", null, null, null,
                "5432trew", null);
        charlie = new Developer(34, "Charles", "Garcia", "charlie",
                "charlie", "chuch@garcia.com", null, null, null,
                "6543ytre", null);
        dan = new Developer(45, "Dan", "Martin", "dan",
                "dan", "dan@martin.com", null, null, null,
                "7654fda", null);
        ed = new Developer(56, "Ed", "Karaz", "ed",
                "ed", "ed@kar.com", null, null, null,
                "5678dfgh", null);

        // websites
        facebook = new Website(123, "Facebook","an online social media and social networking service", gradeDate, gradeDate, 1234234, null, alice.getId());
        twitter = new Website(234, "Twitter","an online news and social networking service", gradeDate, gradeDate, 4321543, null, bob.getId());
        wiki = new Website(345, "Wikipedia","a free online encyclopedia", gradeDate, gradeDate, 3456654, null, charlie.getId());
        cnn = new Website(456, "CNN","an American basic cable and satellite television news channel", gradeDate, gradeDate, 6543345, null, alice.getId());
        cnet = new Website(567, "CNET","an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", gradeDate, gradeDate, 5433455, null, bob.getId());
        gizmodo = new Website(678, "Gizmodo","a design, technology, science and science fiction website that also writes articles on politics", gradeDate, gradeDate, 4322345, null, charlie.getId());

        // pages
        home = new Page(123, "Home", "Landing page", semesterDate, dueDate, 123434, null, cnet.getId());
        about = new Page(234, "About", "Website description", semesterDate, dueDate, 234545, null, gizmodo.getId());
        contact = new Page(345, "Contact", "Addresses, phones, and contact info", semesterDate, dueDate, 345656, null, wiki.getId());
        preferences = new Page(456, "Preferences", "Where users can configure their preferences", semesterDate, dueDate, 456776, null, cnn.getId());
        profile = new Page(456, "Profile", "Users can configure their personal information", semesterDate, dueDate, 567878, null, cnet.getId());

        // widgets
        int id = 0;
        head123 = new HeadingWidget(++id, "head123", 0, 0, null, null, "Welcome", 0, home.getId(), 0);
        post = new HtmlWidget(++id, "post234", 0, 0, null, null, "<p>Lorem</p>", 0, about.getId(), "<p>Lorem</p>");
        head345 = new HeadingWidget(++id, "head345", 0, 0, null, null, "Hi", 1, contact.getId(), 0);
        intro = new HtmlWidget(++id, "intro456", 0, 0, null, null, "<h1>Hi</h1>", 2, contact.getId(), "<h1>Hi</h1>");
        image = new ImageWidget(++id, "image345", 50, 100, null, null, null, 3, contact.getId(), "/img/567.png");
        video = new YouTubeWidget(++id, "video456", 400, 300, null, null, null, 0, preferences.getId(), "https://youtu.be/h67VX51QXiQ", false, false);

        // roles
        id = 0;
        owner = new Role(++id, "owner");
        admin = new Role(++id, "admin");
        writer = new Role(++id, "writer");
        editor = new Role(++id, "editor");
        reviewer = new Role(++id, "reviewer");

        // priviledges
        id = 0;
        create = new Priviledge(++id, "create");
        read = new Priviledge(++id, "read");
        update = new Priviledge(++id, "update");
        delete = new Priviledge(++id, "delete");
    }


    public static void main(String[] args) {
        System.out.println("Loading roles...");
        loadRoles();

        System.out.println("Loading priviledges ...");
        loadPriviledges();

        System.out.println("Loading developers...");
        loadDevelopers();

        System.out.println("Loading websites...");
        loadWebsites();

        System.out.println("Loading pages...");
        loadPages();

        System.out.println("Loading roles...");
        loadWidget();

        System.out.println("Assigning roles...");
        assignRoles();

        System.out.println("Assigning priviledges...");
        assignPrivildeges();
    }

    private static void loadDevelopers() {
        Developer[] developers = {alice, bob, charlie, dan, ed};

        DeveloperDao dao = DeveloperDao.getInstance();
        Arrays.stream(developers).forEach(dao::createDeveloper);
    }

    private static void loadWebsites() {
        Website[] websites = {facebook, twitter, wiki, cnn, cnet, gizmodo};

        WebsiteDao dao = WebsiteDao.getInstance();
        Arrays.stream(websites).forEach(website -> dao.createWebsiteForDeveloper(website.getDeveloperId(), website));
    }

    private static void loadPages() {
        Page[] pages = {home, about, contact, preferences, profile};

        PageDao dao = PageDao.getInstance();
        Arrays.stream(pages).forEach(page -> dao.createPageForWebsite(page.getWebsitdId(), page));
    }

    private static void loadWidget() {
        Widget[] widgets = {head123, post, head345, intro, image, video};

        WidgetDao dao = WidgetDao.getInstance();
        Arrays.stream(widgets).forEach(widget -> dao.createWidgetForPage(widget.getPageId(), widget));
    }

    private static void loadRoles() {
        RoleDao dao = RoleDao.getInstance();

        Role[] roles = {owner, admin, writer, editor, reviewer};
        Arrays.stream(roles).forEach(dao::createRole);
    }

    private static void loadPriviledges() {
        PriviledgeDao dao = PriviledgeDao.getInstance();

        Priviledge[] priviledges = {create, read, update, delete};
        Arrays.stream(priviledges).forEach(dao::createPriviledge);
    }

    private static void assignRoles() {
        RoleDao dao = RoleDao.getInstance();

        // website
        dao.assignWebsiteRole(alice.getId(), facebook.getId(), owner.getId());
        dao.assignWebsiteRole(bob.getId(), facebook.getId(), editor.getId());
        dao.assignWebsiteRole(charlie.getId(), facebook.getId(), admin.getId());

        dao.assignWebsiteRole(bob.getId(), twitter.getId(), owner.getId());
        dao.assignWebsiteRole(charlie.getId(), twitter.getId(), editor.getId());
        dao.assignWebsiteRole(alice.getId(), twitter.getId(), admin.getId());

        dao.assignWebsiteRole(charlie.getId(), wiki.getId(), owner.getId());
        dao.assignWebsiteRole(alice.getId(), wiki.getId(), editor.getId());
        dao.assignWebsiteRole(bob.getId(), wiki.getId(), admin.getId());

        dao.assignWebsiteRole(alice.getId(), cnn.getId(), owner.getId());
        dao.assignWebsiteRole(bob.getId(), cnn.getId(), editor.getId());
        dao.assignWebsiteRole(charlie.getId(), cnn.getId(), admin.getId());

        dao.assignWebsiteRole(bob.getId(), cnet.getId(), owner.getId());
        dao.assignWebsiteRole(charlie.getId(), cnet.getId(), editor.getId());
        dao.assignWebsiteRole(alice.getId(), cnet.getId(), admin.getId());

        dao.assignWebsiteRole(charlie.getId(), gizmodo.getId(), owner.getId());
        dao.assignWebsiteRole(alice.getId(), gizmodo.getId(), editor.getId());
        dao.assignWebsiteRole(bob.getId(), gizmodo.getId(), admin.getId());

        // page
        dao.assignPageRole(alice.getId(), home.getId(), editor.getId());
        dao.assignPageRole(bob.getId(), home.getId(), reviewer.getId());
        dao.assignPageRole(charlie.getId(), home.getId(), writer.getId());

        dao.assignPageRole(bob.getId(), about.getId(), editor.getId());
        dao.assignPageRole(charlie.getId(), about.getId(), reviewer.getId());
        dao.assignPageRole(alice.getId(), about.getId(), writer.getId());

        dao.assignPageRole(charlie.getId(), contact.getId(), editor.getId());
        dao.assignPageRole(alice.getId(), contact.getId(), reviewer.getId());
        dao.assignPageRole(bob.getId(), contact.getId(), writer.getId());

        dao.assignPageRole(alice.getId(), preferences.getId(), editor.getId());
        dao.assignPageRole(bob.getId(), preferences.getId(), reviewer.getId());
        dao.assignPageRole(charlie.getId(), preferences.getId(), writer.getId());

        dao.assignPageRole(bob.getId(), profile.getId(), editor.getId());
        dao.assignPageRole(charlie.getId(), profile.getId(), reviewer.getId());
        dao.assignPageRole(alice.getId(), profile.getId(), writer.getId());
    }

    private static void assignPrivildeges() {

    }
}
