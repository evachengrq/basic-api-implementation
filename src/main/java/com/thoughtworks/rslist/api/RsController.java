package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEvent;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RsController {

  private List<RsEvent> rsList = initRsList();

  private List<RsEvent> initRsList() {
    List<RsEvent> tempList = new ArrayList<>();
    tempList.add(new RsEvent("第一条事件", "无分类"));
    tempList.add(new RsEvent("第二条事件", "无分类"));
    tempList.add(new RsEvent("第三条事件", "无分类"));
    return tempList;
  }

  @GetMapping("/rs/{index}")
  public RsEvent getOneRsEvent(@PathVariable int index) {
    return rsList.get(index - 1);
  }

  @GetMapping("rs/list")
  public List<RsEvent> getRsEventByRange(@RequestParam(required = false) Integer start,
                                         @RequestParam(required = false) Integer end) {
    if (start == null || end == null) {
      return rsList;
    }
    return rsList.subList(start - 1, end);
  }

  @PostMapping("rs/event")
  public void addRsEvent(@RequestBody RsEvent rsEventString) {
    rsList.add(rsEventString);
  }

  @PutMapping("/rs/modify/{index}")
  public void modifyRsEvent(@PathVariable int index, @RequestBody RsEvent rsEvent) {
    rsList.set(index - 1, rsEvent);
  }
}