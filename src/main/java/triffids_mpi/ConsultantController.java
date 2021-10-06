package triffids_mpi;

import triffids_mpi.domain.OrderCard;
import triffids_mpi.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {
    @Autowired
    private OrderRepo orderRepo;

    public void putVariables(Map<String, Object> model, Integer id, String name, String customer, String date, String deadline, String quality, Integer quantity, String notes) {
        Integer flag;
        if (orderRepo == null)
            flag = 0;
        Iterable<OrderCard> orders = orderRepo.findAll();
        model.put("orders", orders);
        model.put("order_id", id);
        model.put("order_name", name);
        model.put("order_customer", customer);
        model.put("order_date", date);
        model.put("order_deadline", deadline);
        model.put("order_quality", quality);
        model.put("order_quantity", quantity);
        model.put("order_notes", notes);
    }

    public OrderCard findOrder(Integer id) {
        return orderRepo.findById(id).orElse(new OrderCard());
    }

    @GetMapping
    public String start(Map<String, Object> model) {

        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }

    @PostMapping("/create")
    public String createNewOrder(
            @RequestParam String newOrder_name,
            @RequestParam String newOrder_customer,
            @RequestParam String newOrder_date,
            @RequestParam String newOrder_deadline,
            @RequestParam String newOrder_quality,
            @RequestParam(defaultValue = "0") Integer newOrder_quantity,
            @RequestParam(defaultValue = "") String newOrder_notes,
            Map<String, Object> model
    ) {
        OrderCard newOrder = new OrderCard(newOrder_name, newOrder_customer, newOrder_date, newOrder_deadline, newOrder_quality, newOrder_quantity, newOrder_notes);

        putVariables(model, 0, "", "", "", "", "", 0, "");

        if (!newOrder_name.equals("") && !newOrder_customer.equals("") && !newOrder_date.equals("") && !newOrder_deadline.equals("") && !newOrder_quality.equals("") && !newOrder_quantity.equals("0")) {
            orderRepo.save(newOrder);
        }

        return "consultant_temp";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam Integer deleteOrder_id, Map<String, Object> model) {
        orderRepo.deleteById(deleteOrder_id);

        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }

    @PostMapping("/choose")
    public String chooseOrder(
            @RequestParam Integer chooseOrder_id,
            Map<String, Object> model
    ) {
        OrderCard order = findOrder(chooseOrder_id);
        String order_name = order.getOrdername();
        String order_customer = order.getCustomer();
        String order_date = order.getOrderdate();
        String order_deadline = order.getOrderdeadline();
        String order_quality = order.getQuality();
        Integer order_quantity = order.getQuantity();
        String order_notes = order.getNotes();

        putVariables(model, chooseOrder_id, order_name, order_customer, order_date, order_deadline, order_quality, order_quantity, order_notes);

        return "consultant_temp";
    }

    @PostMapping("/edit")
    public String editOrder(
            @RequestParam(defaultValue = "0") Integer order_id,
            @RequestParam String order_name,
            @RequestParam String order_customer,
            @RequestParam String order_date,
            @RequestParam String order_deadline,
            @RequestParam String order_quality,
            @RequestParam(defaultValue = "0") Integer order_quantity,
            @RequestParam(defaultValue = "") String order_notes,
            Map<String, Object> model
    ) {
        if (order_id != 0 && !order_name.equals("") && !order_customer.equals("") && !order_date.equals("") && !order_deadline.equals("") && !order_quality.equals("") && !order_quantity.equals("0")) {
            OrderCard order = findOrder(order_id);

            order.setOrdername(order_name);
            order.setCustomer(order_customer);
            order.setOrderdate(order_date);
            order.setOrderdeadline(order_deadline);
            order.setQuality(order_quality);
            order.setQuantity(order_quantity);
            order.setNotes(order_notes);

            orderRepo.save(order);
        }

        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }

    @PostMapping("/cancel")
    public String cancelOrderEdition(Map<String, Object> model) {
        putVariables(model, 0, "", "", "", "", "", 0, "");

        return "consultant_temp";
    }
}