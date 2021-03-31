<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请人ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入申请人ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标书名称" prop="bidName">
        <el-input
          v-model="queryParams.bidName"
          placeholder="请输入标书名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标书编号" prop="bidNumber">
        <el-input
          v-model="queryParams.bidNumber"
          placeholder="请输入标书编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投标截止日期" prop="bidDeadline">
        <el-date-picker clearable size="small"
          v-model="queryParams.bidDeadline"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择投标截止日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="投标日期" prop="bidDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.bidDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择投标日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="中标日期" prop="winningDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.winningDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择中标日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="开标日期" prop="bidOpeningDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.bidOpeningDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开标日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="招标代理公司" prop="tenderAgency">
        <el-input
          v-model="queryParams.tenderAgency"
          placeholder="请输入招标代理公司"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="招标单位" prop="biddingUnit">
        <el-input
          v-model="queryParams.biddingUnit"
          placeholder="请输入招标单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报价(元)" prop="quotedPrice">
        <el-input
          v-model="queryParams.quotedPrice"
          placeholder="请输入报价(元)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="建设地点" prop="constructionSite">
        <el-input
          v-model="queryParams.constructionSite"
          placeholder="请输入建设地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投标保证金(元)" prop="bidBond">
        <el-input
          v-model="queryParams.bidBond"
          placeholder="请输入投标保证金(元)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否中标(0否 1是)" prop="winBid">
        <el-input
          v-model="queryParams.winBid"
          placeholder="请输入是否中标(0否 1是)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投标保证金是否收回(0否 1是)" prop="bidBondRecovered">
        <el-input
          v-model="queryParams.bidBondRecovered"
          placeholder="请输入投标保证金是否收回(0否 1是)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投标保证金收回日期" prop="bidBondTakebackDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.bidBondTakebackDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择投标保证金收回日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="履约保证金(元)" prop="performBond">
        <el-input
          v-model="queryParams.performBond"
          placeholder="请输入履约保证金(元)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="履约保证金是否收回(0否 1是)" prop="performBondRecovered">
        <el-input
          v-model="queryParams.performBondRecovered"
          placeholder="请输入履约保证金是否收回(0否 1是)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="履约保证金收回日期" prop="performBondTakebackDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.performBondTakebackDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择履约保证金收回日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="流程实例ID" prop="instanceId">
        <el-input
          v-model="queryParams.instanceId"
          placeholder="请输入流程实例ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)" prop="applyStatus">
        <el-select v-model="queryParams.applyStatus" placeholder="请选择申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="申请时间" prop="applyTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.applyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择申请时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="投标项目" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入投标项目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目类别" prop="projectCategory">
        <el-input
          v-model="queryParams.projectCategory"
          placeholder="请输入项目类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目负责人" prop="projectManager">
        <el-input
          v-model="queryParams.projectManager"
          placeholder="请输入项目负责人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目负责人姓名" prop="projectManagerName">
        <el-input
          v-model="queryParams.projectManagerName"
          placeholder="请输入项目负责人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目负责人电话" prop="projectManagerContact">
        <el-input
          v-model="queryParams.projectManagerContact"
          placeholder="请输入项目负责人电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经办人" prop="agent">
        <el-input
          v-model="queryParams.agent"
          placeholder="请输入经办人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经办人姓名" prop="agentName">
        <el-input
          v-model="queryParams.agentName"
          placeholder="请输入经办人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经办人电话" prop="agentContact">
        <el-input
          v-model="queryParams.agentContact"
          placeholder="请输入经办人电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目类型" prop="projectType">
        <el-select v-model="queryParams.projectType" placeholder="请选择项目类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="规模" prop="scale">
        <el-input
          v-model="queryParams.scale"
          placeholder="请输入规模"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务类别" prop="businessCategory">
        <el-input
          v-model="queryParams.businessCategory"
          placeholder="请输入业务类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联合单位名称" prop="unionCompanyName">
        <el-input
          v-model="queryParams.unionCompanyName"
          placeholder="请输入联合单位名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投标地点" prop="bidPlace">
        <el-input
          v-model="queryParams.bidPlace"
          placeholder="请输入投标地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['project:bidinfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:bidinfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:bidinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:bidinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bidinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请ID" align="center" prop="bidId" />
      <el-table-column label="申请人ID" align="center" prop="userId" />
      <el-table-column label="标书名称" align="center" prop="bidName" />
      <el-table-column label="标书编号" align="center" prop="bidNumber" />
      <el-table-column label="投标截止日期" align="center" prop="bidDeadline" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bidDeadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投标日期" align="center" prop="bidDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bidDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="中标日期" align="center" prop="winningDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.winningDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开标日期" align="center" prop="bidOpeningDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bidOpeningDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="招标代理公司" align="center" prop="tenderAgency" />
      <el-table-column label="招标单位" align="center" prop="biddingUnit" />
      <el-table-column label="报价(元)" align="center" prop="quotedPrice" />
      <el-table-column label="建设地点" align="center" prop="constructionSite" />
      <el-table-column label="投标保证金(元)" align="center" prop="bidBond" />
      <el-table-column label="是否中标(0否 1是)" align="center" prop="winBid" />
      <el-table-column label="投标保证金是否收回(0否 1是)" align="center" prop="bidBondRecovered" />
      <el-table-column label="投标保证金收回日期" align="center" prop="bidBondTakebackDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bidBondTakebackDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投标保证金未收回原因" align="center" prop="bidBondReasonForFailure" />
      <el-table-column label="履约保证金(元)" align="center" prop="performBond" />
      <el-table-column label="履约保证金是否收回(0否 1是)" align="center" prop="performBondRecovered" />
      <el-table-column label="履约保证金收回日期" align="center" prop="performBondTakebackDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.performBondTakebackDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="履约保证金未收回原因" align="center" prop="performBondReasonForFailure" />
      <el-table-column label="中标通知书附件信息(前端自主构建JSON格式字符串)" align="center" prop="bidAttachment" />
      <el-table-column label="流程实例ID" align="center" prop="instanceId" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)" align="center" prop="applyStatus" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="申请时间" align="center" prop="applyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投标项目" align="center" prop="projectId" />
      <el-table-column label="项目类别" align="center" prop="projectCategory" />
      <el-table-column label="项目负责人" align="center" prop="projectManager" />
      <el-table-column label="项目负责人姓名" align="center" prop="projectManagerName" />
      <el-table-column label="项目负责人电话" align="center" prop="projectManagerContact" />
      <el-table-column label="经办人" align="center" prop="agent" />
      <el-table-column label="经办人姓名" align="center" prop="agentName" />
      <el-table-column label="经办人电话" align="center" prop="agentContact" />
      <el-table-column label="项目类型" align="center" prop="projectType" />
      <el-table-column label="规模" align="center" prop="scale" />
      <el-table-column label="业务类别" align="center" prop="businessCategory" />
      <el-table-column label="联合单位名称" align="center" prop="unionCompanyName" />
      <el-table-column label="投标地点" align="center" prop="bidPlace" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:bidinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:bidinfo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改投标信息申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="申请人ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入申请人ID" />
        </el-form-item>
        <el-form-item label="标书名称" prop="bidName">
          <el-input v-model="form.bidName" placeholder="请输入标书名称" />
        </el-form-item>
        <el-form-item label="标书编号" prop="bidNumber">
          <el-input v-model="form.bidNumber" placeholder="请输入标书编号" />
        </el-form-item>
        <el-form-item label="投标截止日期" prop="bidDeadline">
          <el-date-picker clearable size="small"
            v-model="form.bidDeadline"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择投标截止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投标日期" prop="bidDate">
          <el-date-picker clearable size="small"
            v-model="form.bidDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择投标日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="中标日期" prop="winningDate">
          <el-date-picker clearable size="small"
            v-model="form.winningDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择中标日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开标日期" prop="bidOpeningDate">
          <el-date-picker clearable size="small"
            v-model="form.bidOpeningDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开标日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="招标代理公司" prop="tenderAgency">
          <el-input v-model="form.tenderAgency" placeholder="请输入招标代理公司" />
        </el-form-item>
        <el-form-item label="招标单位" prop="biddingUnit">
          <el-input v-model="form.biddingUnit" placeholder="请输入招标单位" />
        </el-form-item>
        <el-form-item label="报价(元)" prop="quotedPrice">
          <el-input v-model="form.quotedPrice" placeholder="请输入报价(元)" />
        </el-form-item>
        <el-form-item label="建设地点" prop="constructionSite">
          <el-input v-model="form.constructionSite" placeholder="请输入建设地点" />
        </el-form-item>
        <el-form-item label="投标保证金(元)" prop="bidBond">
          <el-input v-model="form.bidBond" placeholder="请输入投标保证金(元)" />
        </el-form-item>
        <el-form-item label="是否中标(0否 1是)" prop="winBid">
          <el-input v-model="form.winBid" placeholder="请输入是否中标(0否 1是)" />
        </el-form-item>
        <el-form-item label="投标保证金是否收回(0否 1是)" prop="bidBondRecovered">
          <el-input v-model="form.bidBondRecovered" placeholder="请输入投标保证金是否收回(0否 1是)" />
        </el-form-item>
        <el-form-item label="投标保证金收回日期" prop="bidBondTakebackDate">
          <el-date-picker clearable size="small"
            v-model="form.bidBondTakebackDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择投标保证金收回日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投标保证金未收回原因" prop="bidBondReasonForFailure">
          <el-input v-model="form.bidBondReasonForFailure" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="履约保证金(元)" prop="performBond">
          <el-input v-model="form.performBond" placeholder="请输入履约保证金(元)" />
        </el-form-item>
        <el-form-item label="履约保证金是否收回(0否 1是)" prop="performBondRecovered">
          <el-input v-model="form.performBondRecovered" placeholder="请输入履约保证金是否收回(0否 1是)" />
        </el-form-item>
        <el-form-item label="履约保证金收回日期" prop="performBondTakebackDate">
          <el-date-picker clearable size="small"
            v-model="form.performBondTakebackDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择履约保证金收回日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="履约保证金未收回原因" prop="performBondReasonForFailure">
          <el-input v-model="form.performBondReasonForFailure" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="中标通知书附件信息(前端自主构建JSON格式字符串)" prop="bidAttachment">
          <el-input v-model="form.bidAttachment" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="流程实例ID" prop="instanceId">
          <el-input v-model="form.instanceId" placeholder="请输入流程实例ID" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)">
          <el-radio-group v-model="form.applyStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="删除标识" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标识" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="申请时间" prop="applyTime">
          <el-date-picker clearable size="small"
            v-model="form.applyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择申请时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投标项目" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入投标项目" />
        </el-form-item>
        <el-form-item label="项目类别" prop="projectCategory">
          <el-input v-model="form.projectCategory" placeholder="请输入项目类别" />
        </el-form-item>
        <el-form-item label="项目负责人" prop="projectManager">
          <el-input v-model="form.projectManager" placeholder="请输入项目负责人" />
        </el-form-item>
        <el-form-item label="项目负责人姓名" prop="projectManagerName">
          <el-input v-model="form.projectManagerName" placeholder="请输入项目负责人姓名" />
        </el-form-item>
        <el-form-item label="项目负责人电话" prop="projectManagerContact">
          <el-input v-model="form.projectManagerContact" placeholder="请输入项目负责人电话" />
        </el-form-item>
        <el-form-item label="经办人" prop="agent">
          <el-input v-model="form.agent" placeholder="请输入经办人" />
        </el-form-item>
        <el-form-item label="经办人姓名" prop="agentName">
          <el-input v-model="form.agentName" placeholder="请输入经办人姓名" />
        </el-form-item>
        <el-form-item label="经办人电话" prop="agentContact">
          <el-input v-model="form.agentContact" placeholder="请输入经办人电话" />
        </el-form-item>
        <el-form-item label="项目类型" prop="projectType">
          <el-select v-model="form.projectType" placeholder="请选择项目类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="规模" prop="scale">
          <el-input v-model="form.scale" placeholder="请输入规模" />
        </el-form-item>
        <el-form-item label="业务类别" prop="businessCategory">
          <el-input v-model="form.businessCategory" placeholder="请输入业务类别" />
        </el-form-item>
        <el-form-item label="联合单位名称" prop="unionCompanyName">
          <el-input v-model="form.unionCompanyName" placeholder="请输入联合单位名称" />
        </el-form-item>
        <el-form-item label="投标地点" prop="bidPlace">
          <el-input v-model="form.bidPlace" placeholder="请输入投标地点" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBidinfo, getBidinfo, delBidinfo, addBidinfo, updateBidinfo, exportBidinfo } from "@/api/project/bidinfo";

export default {
  name: "Bidinfo",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 投标信息申请表格数据
      bidinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        bidName: null,
        bidNumber: null,
        bidDeadline: null,
        bidDate: null,
        winningDate: null,
        bidOpeningDate: null,
        tenderAgency: null,
        biddingUnit: null,
        quotedPrice: null,
        constructionSite: null,
        bidBond: null,
        winBid: null,
        bidBondRecovered: null,
        bidBondTakebackDate: null,
        bidBondReasonForFailure: null,
        performBond: null,
        performBondRecovered: null,
        performBondTakebackDate: null,
        performBondReasonForFailure: null,
        bidAttachment: null,
        instanceId: null,
        status: null,
        applyStatus: null,
        applyTime: null,
        projectId: null,
        projectCategory: null,
        projectManager: null,
        projectManagerName: null,
        projectManagerContact: null,
        agent: null,
        agentName: null,
        agentContact: null,
        projectType: null,
        scale: null,
        businessCategory: null,
        unionCompanyName: null,
        bidPlace: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询投标信息申请列表 */
    getList() {
      this.loading = true;
      listBidinfo(this.queryParams).then(response => {
        this.bidinfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        bidId: null,
        userId: null,
        bidName: null,
        bidNumber: null,
        bidDeadline: null,
        bidDate: null,
        winningDate: null,
        bidOpeningDate: null,
        tenderAgency: null,
        biddingUnit: null,
        quotedPrice: null,
        constructionSite: null,
        bidBond: null,
        winBid: null,
        bidBondRecovered: null,
        bidBondTakebackDate: null,
        bidBondReasonForFailure: null,
        performBond: null,
        performBondRecovered: null,
        performBondTakebackDate: null,
        performBondReasonForFailure: null,
        bidAttachment: null,
        instanceId: null,
        status: "0",
        applyStatus: "0",
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        applyTime: null,
        projectId: null,
        projectCategory: null,
        projectManager: null,
        projectManagerName: null,
        projectManagerContact: null,
        agent: null,
        agentName: null,
        agentContact: null,
        projectType: null,
        scale: null,
        businessCategory: null,
        unionCompanyName: null,
        bidPlace: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.bidId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加投标信息申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bidId = row.bidId || this.ids
      getBidinfo(bidId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改投标信息申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bidId != null) {
            updateBidinfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBidinfo(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const bidIds = row.bidId || this.ids;
      this.$confirm('是否确认删除投标信息申请编号为"' + bidIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBidinfo(bidIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有投标信息申请数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportBidinfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
