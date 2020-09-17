package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.dto.RsEvent;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RsEvent> getOneRsEvent(@PathVariable int index) {
        return ResponseEntity.ok().body(rsList.get(index));
    }

    @GetMapping("rs/list")
    public ResponseEntity<List<RsEvent>> getRsEventByRange(@RequestParam(required = false) Integer start,
                                           @RequestParam(required = false) Integer end) {
        if (start == null || end == null) {
            return ResponseEntity.ok().body(rsList);
        }
        return ResponseEntity.ok().body(rsList.subList(start - 1, end));
    }

    @PostMapping("rs/event")
    public void addRsEvent(@RequestBody RsEvent rsEventString) {
        rsList.add(rsEventString);
    }

    @PutMapping("/rs/modify/{index}")
    public ResponseEntity<List<RsEvent>> modifyRsEvent(@PathVariable int index, @RequestBody RsEvent rsEvent) {
        rsList.set(index - 1, rsEvent);
        return ResponseEntity.ok().body(rsList);
    }

    @DeleteMapping("/rs/delete/{index}")
    public ResponseEntity<List<RsEvent>> deleteRsEvent(@PathVariable int index) {
        rsList.remove(index - 1);
        return ResponseEntity.ok().body(rsList);
    }
}