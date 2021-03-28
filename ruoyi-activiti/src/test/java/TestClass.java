import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestClass {
//    public String startProcess(ProcessDealDto processDealDto) {
//
//        ProcessInstance instance = runtimeService.startProcessInstanceByKey(WorkFlowConstant.PROCESS_WORKTICKET_ID);
//        //验证是否启动成功
//        //通过查询正在运行的流程实例来判断
//        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
//        //根据流程实例ID来查询
//        List<ProcessInstance> runningList = processInstanceQuery.processInstanceId(instance.getProcessInstanceId()).list();
//        logger.debug(LogFormatter.toMsg("start process", "instance"), runningList);
//
//        String instanceId = instance.getId();
//
//        //设置处理人
//        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
//        if (task != null) {
//            //自动完成第一步
//
//            Map<String, Object> map = new HashMap<String, Object>(2);
//            map.put("check", 1);
//            map.put("personId", processDealDto.getPersonId());
//            taskService.setAssignee(task.getId(), processDealDto.getCurrentPersonId());
//            taskService.complete(task.getId(), map);
//
//            task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
//            taskService.setAssignee(task.getId(), processDealDto.getPersonId());
//        } else {
//            throw new BusinessException(WorkTaskErrorCode.WORK_TASK_START_PROCESS_ERROR);
//        }
//
//        // 返回流程ID
//        return instanceId;
//    }

}
