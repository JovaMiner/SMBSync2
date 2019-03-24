package com.sentaroh.android.SMBSync2;

/*
The MIT License (MIT)
Copyright (c) 2011-2018 Sentaroh

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

*/

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sentaroh on 2018/03/21.
 */
class SyncTaskItem implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private String syncTasｋName = "";
    private String syncTasｋGroup = "";
    private boolean syncTaskEnabled = true;
    private boolean isChecked = false;

    private boolean syncOptionSyncTestMode = false;

    private int syncTaskPosition = 0;

    public final static String SYNC_TASK_TYPE_MIRROR = "M";
    public final static String SYNC_TASK_TYPE_COPY = "C";
    public final static String SYNC_TASK_TYPE_MOVE = "X";
    public final static String SYNC_TASK_TYPE_SYNC = "S";
    public final static String SYNC_TASK_TYPE_ARCHIVE = "A";
    private String syncTaskType = SYNC_TASK_TYPE_MIRROR;

    public final static String SYNC_TASK_TWO_WAY_OPTION_ASK_USER = "0";
    public final static String SYNC_TASK_TWO_WAY_OPTION_COPY_NEWER = "1";
    public final static String SYNC_TASK_TWO_WAY_OPTION_COPY_OLDER = "2";
    public final static String SYNC_TASK_TWO_WAY_OPTION_SKIP_SYNC_FILE = "3";
    public final static String SYNC_TASK_TWO_WAY_CONFLICT_FILE_SUFFIX=".smbsync2_conflict";
    private String syncTwoWayConflictOption =SYNC_TASK_TWO_WAY_OPTION_COPY_NEWER;
    private boolean syncTwoWayConflictKeepConflictFile = false;

    public final static String SYNC_FOLDER_TYPE_INTERNAL = "INT";
    public final static String SYNC_FOLDER_TYPE_SDCARD = "EXT";
    public final static String SYNC_FOLDER_TYPE_USB="USB";
    public final static String SYNC_FOLDER_TYPE_SMB = "SMB";
    public final static String SYNC_FOLDER_TYPE_ZIP = "ZIP";
    private String syncTaskMasterFolderType = SYNC_FOLDER_TYPE_INTERNAL;

    private String syncTaskMasterFolderDirName = "";
    private String syncTaskMasterLocalMountPoint = "";
    private String syncTaskMasterFolderRemoteSmbShareName = "";
    private String syncTaskMasterFolderRemoteIpAddress = "";
    private String syncTaskMasterFolderRemoteHostName = "";
    private String syncTaskMasterFolderRemotePortNumber = "";
    private String syncTaskMasterFolderRemoteUserName = "";
    private String syncTaskMasterFolderRemotePassword = "";
    private String syncTaskMasterFolderRemoteDomain = "";
    public final static String SYNC_FOLDER_SMB_PROTOCOL_SYSTEM = "0";
    public final static String SYNC_FOLDER_SMB_PROTOCOL_SMB1 = "1";
    public final static String SYNC_FOLDER_SMB_PROTOCOL_SMB201 = "2";
    public final static String SYNC_FOLDER_SMB_PROTOCOL_SMB211 = "3";
    public final static String SYNC_FOLDER_SMB_PROTOCOL_SMB212 = "4";
    private String syncTaskMasterFolderSmbProtocol = SYNC_FOLDER_SMB_PROTOCOL_SMB1;
    private boolean syncTaskMasterFolderSmbIpcSigningEnforced = true;
    private String syncTaskMasterFolderRemovableStorageID = "";
//	private boolean syncTaskMasterFolderUseInternalUsbFolder=false;

    private String syncTaskTargetFolderType = SYNC_FOLDER_TYPE_INTERNAL;
    private String syncTaskTargetFolderDirName = "";
    private String syncTaskTargetLocalMountPoint = "";
    private String syncTaskTargetFolderRemoteSmbShareName = "";
    private String syncTaskTargetFolderRemoteIpAddress = "";
    private String syncTaskTargetFolderRemoteHostName = "";
    private String syncTaskTargetFolderRemotePortNumber = "";
    private String syncTaskTargetFolderRemoteUserName = "";
    private String syncTaskTargetFolderRemotePassword = "";
    private String syncTaskTargetFolderRemoteDomain = "";
    private String syncTaskTargetFolderSmbProtocol = SYNC_FOLDER_SMB_PROTOCOL_SMB1;
    private boolean syncTaskTargetFolderSmbIpcSigningEnforced = true;
    private String syncTaskTargetFolderRemovableStorageID = "";

    private String syncTaskTargetZipFileName = "";
    public final static String ZIP_OPTION_COMP_LEVEL_FASTEST = "FASTEST";
    public final static String ZIP_OPTION_COMP_LEVEL_FAST = "FAST";
    public final static String ZIP_OPTION_COMP_LEVEL_NORMAL = "NORMAL";
    public final static String ZIP_OPTION_COMP_LEVEL_MAXIMUM = "MAXIMUM";
    public final static String ZIP_OPTION_COMP_LEVEL_ULTRA = "ULTRA";
    public final static String ZIP_OPTION_COMP_METHOD_STORE = "STORE";
    public final static String ZIP_OPTION_COMP_METHOD_DEFLATE = "DEFLATE";
    public final static String ZIP_OPTION_ENCRYPT_NONE = "NONE";
    public final static String ZIP_OPTION_ENCRYPT_STANDARD = "STANDARD";
    public final static String ZIP_OPTION_ENCRYPT_AES128 = "AES128";
    public final static String ZIP_OPTION_ENCRYPT_AES256 = "AES256";
    private String syncTaskTargetZipCompOptionCompLevel = ZIP_OPTION_COMP_LEVEL_NORMAL;
    private String syncTaskTargetZipCompOptionCompMethod = ZIP_OPTION_COMP_METHOD_DEFLATE;
    private String syncTaskTargetZipCompOptionEncrypt = ZIP_OPTION_ENCRYPT_NONE;
    private String syncTaskTargetZipCompOptionPassword = "";
    private String syncTaskTargetZipCompOptionEncoding = "UTF-8";
    //	private boolean syncTaskTargetZipUseInternalUsbFolder=false;
    private boolean syncTaskTargetZipUseSdcard = false;
    private boolean syncTaskTargetZipUseUsb = false;
//	private boolean syncTaskTargetFolderUseInternalUsbFolder=false;

    private boolean syncFileTypeAudio = false;
    private boolean syncFileTypeImage = false;
    private boolean syncFileTypeVideo = false;

    private ArrayList<String> syncFileFilter = new ArrayList<String>();
    private ArrayList<String> syncDirFilter = new ArrayList<String>();

    private boolean syncOptionRootDirFileToBeProcessed = true;
    private boolean syncOptionProcessOverrideCopyMove = true;
    private boolean syncOptionConfirmOverrideDelete = true;
    private boolean syncOptionForceLastModifiedUseSmbsync = false;
    private boolean syncOptionNotUsedLastModifiedForRemote = false;

    private String syncOptionRetryCount = "3";
    private boolean syncOptionSyncEmptyDir = true;
    private boolean syncTaskTargetUseTakenDateTimeForDirectoryNameKeyword = false;
    private boolean syncOptionSyncHiddenFile = true;
    private boolean syncOptionSyncHiddenDir = true;
    private boolean syncOptionSyncSubDir = true;

    private boolean syncOptionUseSmallIoBuffer = false;

    private boolean syncOptionDeterminChangedFileBySize = true;
    private boolean syncOptionDeterminChangedFileByTime = true;
    private int syncOptionDeterminChangedFileByTimeValue = 3;//Seconds

    private boolean syncOptionDoNotUseRenameWhenSmbFileWrite = false;

    private boolean syncOptionUseExtendedDirectoryFilter1 = false;

    private boolean syncOptionDeleteFirstWhenMirror = false;

    public final static String SYNC_WIFI_STATUS_WIFI_OFF = "0";
    public final static String SYNC_WIFI_STATUS_WIFI_CONNECT_ANY_AP = "1";
    public final static String SYNC_WIFI_STATUS_WIFI_CONNECT_SPECIFIC_AP = "2";
    public final static String SYNC_WIFI_STATUS_WIFI_CONNECT_PRIVATE_ADDR = "3";
    public final static String SYNC_WIFI_STATUS_WIFI_CONNECT_SPECIFIC_ADDR = "4";
    private String syncOptionWifiStatus = SYNC_WIFI_STATUS_WIFI_CONNECT_ANY_AP;
    private boolean syncTaskSkipIfConnectAnotherWifiSsid = false;

    private boolean syncOptionSyncOnlyCharging = false;

    private String syncLastSyncTime = "";
    private int syncLastSyncResult = 0;
    static public final int SYNC_STATUS_SUCCESS = SyncHistoryItem.SYNC_STATUS_SUCCESS;
    static public final int SYNC_STATUS_CANCEL = SyncHistoryItem.SYNC_STATUS_CANCEL;
    static public final int SYNC_STATUS_ERROR = SyncHistoryItem.SYNC_STATUS_ERROR;
    static public final int SYNC_STATUS_WARNING = SyncHistoryItem.SYNC_STATUS_WARNING;

    //Not save variables
    private boolean syncTaskIsRunning = false;

    public SyncTaskItem(String stn, boolean pfa, boolean ic) {
        syncTasｋName = stn;
        syncTaskEnabled = pfa;
        isChecked = ic;
    }

    public SyncTaskItem() {
    }

    public String getSyncTaskName() {return syncTasｋName;}

    public String getSyncTaskType() {return syncTaskType;}

    public void setSyncTaskType(String p) {syncTaskType = p;}

    public void setSyncTaskAuto(boolean p) {syncTaskEnabled = p;}

    public boolean isSyncTaskAuto() {return syncTaskEnabled;}

    public String getSyncTaskGroup() {return syncTasｋGroup;}

    public void setSyncTaskGroup(String grp_name) {syncTasｋGroup = grp_name;}

    public void setSyncTwoWayConflictFileRule(String p) {syncTwoWayConflictOption = p;}
    public String getSyncTwoWayConflictFileRule() {return syncTwoWayConflictOption;}

    public void setSyncTwoWayKeepConflictFile(boolean keep_file) {syncTwoWayConflictKeepConflictFile=keep_file;}
    public boolean isSyncTwoWayKeepConflictFile() {return syncTwoWayConflictKeepConflictFile;}

    public String getMasterSmbUserName() {return syncTaskMasterFolderRemoteUserName;}

    public String getMasterSmbPassword() {return syncTaskMasterFolderRemotePassword;}

    public String getMasterRemoteSmbShareName() {return syncTaskMasterFolderRemoteSmbShareName;}

    public String getMasterDirectoryName() {return syncTaskMasterFolderDirName;}

    public String getMasterLocalMountPoint() {return syncTaskMasterLocalMountPoint;}

    public String getMasterSmbAddr() {return syncTaskMasterFolderRemoteIpAddress;}

    public String getMasterSmbPort() {return syncTaskMasterFolderRemotePortNumber;}

    public String getMasterSmbHostName() {return syncTaskMasterFolderRemoteHostName;}

    public String getMasterSmbDomain() {return syncTaskMasterFolderRemoteDomain;}

    public String getMasterSmbProtocol() {return syncTaskMasterFolderSmbProtocol;}

    public void setMasterSmbProtocol(String proto) {syncTaskMasterFolderSmbProtocol=proto;}

    public String getMasterRemovableStorageID() {return syncTaskMasterFolderRemovableStorageID;}

    public boolean isMasterSmbIpcSigningEnforced() {return syncTaskMasterFolderSmbIpcSigningEnforced;}
    public void setMasterSmbIpcSigningEnforced(boolean enforced) {syncTaskMasterFolderSmbIpcSigningEnforced=enforced;}

    public String getMasterFolderType() {return syncTaskMasterFolderType;}

    public String getTargetSmbUserName() {return syncTaskTargetFolderRemoteUserName;}

    public String getTargetSmbPassword() {return syncTaskTargetFolderRemotePassword;}

    public String getTargetSmbShareName() {return syncTaskTargetFolderRemoteSmbShareName;}

    public String getTargetDirectoryName() {return syncTaskTargetFolderDirName;}

    public String getTargetLocalMountPoint() {return syncTaskTargetLocalMountPoint;}

    public String getTargetSmbAddr() {return syncTaskTargetFolderRemoteIpAddress;}

    public String getTargetSmbPort() {return syncTaskTargetFolderRemotePortNumber;}

    public String getTargetSmbHostName() {return syncTaskTargetFolderRemoteHostName;}

    public String getTargetSmbDomain() {return syncTaskTargetFolderRemoteDomain;}

    public String getTargetSmbProtocol() {return syncTaskTargetFolderSmbProtocol;}

    public void setTargetSmbProtocol(String proto) {syncTaskTargetFolderSmbProtocol=proto;}

    public boolean isTargetSmbIpcSigningEnforced() {return syncTaskTargetFolderSmbIpcSigningEnforced;}

    public void setTargetSmbIpcSigningEnforced(boolean enforced) {syncTaskTargetFolderSmbIpcSigningEnforced=enforced;}

    public String getTargetRemovableStorageID() {return syncTaskTargetFolderRemovableStorageID;}

    public String getTargetZipOutputFileName() {return syncTaskTargetZipFileName;}

    public String getTargetFolderType() {return syncTaskTargetFolderType;}

    public String getTargetZipCompressionLevel() {return syncTaskTargetZipCompOptionCompLevel;}
    public void setTargetZipCompressionLevel(String p) {syncTaskTargetZipCompOptionCompLevel = p;}

    public String getTargetZipCompressionMethod() {return syncTaskTargetZipCompOptionCompMethod;}
    public void setTargetZipCompressionMethod(String p) {syncTaskTargetZipCompOptionCompMethod = p;}

    public String getTargetZipEncryptMethod() {return syncTaskTargetZipCompOptionEncrypt;}
    public void setTargetZipEncryptMethod(String p) {syncTaskTargetZipCompOptionEncrypt = p;}

    public String getTargetZipPassword() {return syncTaskTargetZipCompOptionPassword;}
    public void setTargetZipPassword(String p) {syncTaskTargetZipCompOptionPassword = p;}

    public boolean isTargetZipUseExternalSdcard() {return syncTaskTargetZipUseSdcard;}
    public void setTargetZipUseExternalSdcard(boolean p) {syncTaskTargetZipUseSdcard = p;}

    public boolean isTargetZipUseUsb() {return syncTaskTargetZipUseUsb;}
    public void setTargetZipUseUsb(boolean p) {syncTaskTargetZipUseUsb = p;}

    public String getTargetZipFileNameEncoding() {return syncTaskTargetZipCompOptionEncoding;}

    public void setTargetZipFileNameEncoding(String p) {syncTaskTargetZipCompOptionEncoding = p;}

    public final static String PICTURE_ARCHIVE_RENAME_KEYWORD_DATE = "%DATE%";
    public final static String PICTURE_ARCHIVE_RENAME_KEYWORD_TIME = "%TIME%";
    public final static String PICTURE_ARCHIVE_RENAME_KEYWORD_ORIGINAL_NAME = "%ORIGINAL-NAME%";
    public final static String PICTURE_ARCHIVE_RENAME_KEYWORD_YEAR = "%YEAR%";
    public final static String PICTURE_ARCHIVE_RENAME_KEYWORD_MONTH = "%MONTH%";
    public final static String PICTURE_ARCHIVE_RENAME_KEYWORD_DAY = "%DAY%";
    private String syncTaskArchiveRenameFileTemplate = "DSC_%DATE%";
    private String syncTaskArchiveSaveDirectoryTemplate = "%YEAR%";

    private boolean syncTaskArchiveRenameWhenArchive = true;

    public final static int PICTURE_ARCHIVE_RETAIN_FOR_A_0_DAYS = 0;
    public final static int PICTURE_ARCHIVE_RETAIN_FOR_A_7_DAYS = 1;
    public final static int PICTURE_ARCHIVE_RETAIN_FOR_A_30_DAYS = 2;
    public final static int PICTURE_ARCHIVE_RETAIN_FOR_A_60_DAYS = 3;
    public final static int PICTURE_ARCHIVE_RETAIN_FOR_A_90_DAYS = 4;
    public final static int PICTURE_ARCHIVE_RETAIN_FOR_A_180_DAYS = 5;
    public final static int PICTURE_ARCHIVE_RETAIN_FOR_A_1_YEARS = 6;
    private int syncTaskArchiveRetentionPeriod = PICTURE_ARCHIVE_RETAIN_FOR_A_180_DAYS;

    private boolean syncTaskArchiveCreateDirectory = false;

    private boolean syncTaskArchiveEnable = true;

    private String syncTaskArchiveSuffixDigit = "4";//0=Not used, 3=3 Digit, 4=4 digit, 5=5 Digit, 6=6 Digit

    public String getArchiveSuffixOption() {return syncTaskArchiveSuffixDigit;}
    public void setArchiveSuffixOption(String digit) {syncTaskArchiveSuffixDigit =digit;}

    public String getArchiveRenameFileTemplate() {return syncTaskArchiveRenameFileTemplate;}
    public void setArchiveRenameFileTemplate(String template) {syncTaskArchiveRenameFileTemplate =template;}

    public String getArchiveCreateDirectoryTemplate() {return syncTaskArchiveSaveDirectoryTemplate;}
    public void setArchiveCreateDirectoryTemplate(String template) {syncTaskArchiveSaveDirectoryTemplate=template;}

    public boolean isArchiveUseRename() {return syncTaskArchiveRenameWhenArchive;}
    public void setArchiveUseRename(boolean rename) {syncTaskArchiveRenameWhenArchive =rename;}
    public int getArchiveRetentionPeriod() {return syncTaskArchiveRetentionPeriod;}
    public void setArchiveRetentionPeriod(int period) {syncTaskArchiveRetentionPeriod =period;}
    public boolean isArchiveCreateDirectory() {return syncTaskArchiveCreateDirectory;}
    public void setArchiveCreateDirectory(boolean cretae) {syncTaskArchiveCreateDirectory =cretae;}
    public boolean isArchiveEnabled() {return syncTaskArchiveEnable;}
    public void setArchiveEnabled(boolean enabled) {syncTaskArchiveEnable =enabled;}

    private boolean syncOptionDeterminChangedFileSizeGreaterThanTargetFile = false;
    public boolean isSyncDifferentFileSizeGreaterThanTagetFile() {return syncOptionDeterminChangedFileSizeGreaterThanTargetFile;}
    public void setSyncDifferentFileSizeGreaterThanTagetFile(boolean p) {syncOptionDeterminChangedFileSizeGreaterThanTargetFile = p;}

    public ArrayList<String> getFileFilter() {return syncFileFilter;}

    public ArrayList<String> getDirFilter() {return syncDirFilter;}

    public boolean isSyncProcessRootDirFile() {return syncOptionRootDirFileToBeProcessed;}

    public void setSyncProcessRootDirFile(boolean p) {syncOptionRootDirFileToBeProcessed = p;}

    public boolean isSyncOverrideCopyMoveFile() {return syncOptionProcessOverrideCopyMove;}

    public void setSyncOverrideCopyMoveFile(boolean p) {syncOptionProcessOverrideCopyMove = p;}

    public boolean isSyncConfirmOverrideOrDelete() {return syncOptionConfirmOverrideDelete;}

    public void setSyncConfirmOverrideOrDelete(boolean p) {syncOptionConfirmOverrideDelete = p;}

    public boolean isSyncDetectLastModifiedBySmbsync() {return syncOptionForceLastModifiedUseSmbsync;}

    public void setSyncDetectLastModidiedBySmbsync(boolean p) {syncOptionForceLastModifiedUseSmbsync = p;}

    public boolean isChecked() {return isChecked;}

    public void setChecked(boolean p) {isChecked = p;}

    public boolean isSyncDoNotResetFileLastModified() {return syncOptionNotUsedLastModifiedForRemote;}

    public void setSyncDoNotResetFileLastModified(boolean p) {syncOptionNotUsedLastModifiedForRemote = p;}

    public void setSyncTaskName(String p) {syncTasｋName = p;}

    public void setMasterSmbUserName(String p) {syncTaskMasterFolderRemoteUserName = p;}

    public void setMasterSmbPassword(String p) {syncTaskMasterFolderRemotePassword = p;}

    public void setMasterSmbShareName(String p) {syncTaskMasterFolderRemoteSmbShareName = p;}

    public void setMasterDirectoryName(String p) {syncTaskMasterFolderDirName = p;}

    public void setMasterLocalMountPoint(String mp) {syncTaskMasterLocalMountPoint = mp;}

    public void setMasterSmbAddr(String p) {syncTaskMasterFolderRemoteIpAddress = p;}
    public void setMasterSmbPort(String p) {syncTaskMasterFolderRemotePortNumber = p;}

    public void setMasterSmbHostName(String p) {syncTaskMasterFolderRemoteHostName = p;}
    public void setMasterSmbDomain(String p) {syncTaskMasterFolderRemoteDomain = p;}

    public void setMasterRemovableStorageID(String p) {syncTaskMasterFolderRemovableStorageID = p;}
    public void setMasterFolderType(String p) {syncTaskMasterFolderType = p;}

    public void setTargetZipOutputFileName(String p) {syncTaskTargetZipFileName = p;}
    public void setTargetSmbUserName(String p) {syncTaskTargetFolderRemoteUserName = p;}

    public void setTargetSmbPassword(String p) {syncTaskTargetFolderRemotePassword = p;}
    public void setTargetSmbShareName(String p) {syncTaskTargetFolderRemoteSmbShareName = p;}

    public void setTargetDirectoryName(String p) {syncTaskTargetFolderDirName = p;}
    public void setTargetLocalMountPoint(String mp) {syncTaskTargetLocalMountPoint = mp;}

    public void setTargetRemoteAddr(String p) {syncTaskTargetFolderRemoteIpAddress = p;}
    public void setTargetRemotePort(String p) {syncTaskTargetFolderRemotePortNumber = p;}

    public void setTargetRemoteHostname(String p) {syncTaskTargetFolderRemoteHostName = p;}
    public void setTargetRemoteDomain(String p) {syncTaskTargetFolderRemoteDomain = p;}

    public void setTargetRemovableStorageID(String p) {syncTaskTargetFolderRemovableStorageID = p;}
    public void setTargetFolderType(String p) {syncTaskTargetFolderType = p;}

    public boolean isTargetUseTakenDateTimeToDirectoryNameKeyword() {return syncTaskTargetUseTakenDateTimeForDirectoryNameKeyword;}
    public void setTargetUseTakenDateTimeToDirectoryNameKeyword(boolean p) {syncTaskTargetUseTakenDateTimeForDirectoryNameKeyword = p;}

    public boolean isSyncFileTypeAudio() {return syncFileTypeAudio;}
    public void setSyncFileTypeAudio(boolean p) {syncFileTypeAudio = p;}

    public boolean isSyncFileTypeImage() {return syncFileTypeImage;}
    public void setSyncFileTypeImage(boolean p) {syncFileTypeImage = p;}

    public boolean isSyncFileTypeVideo() {return syncFileTypeVideo;}
    public void setSyncFileTypeVideo(boolean p) {syncFileTypeVideo = p;}

    public void setFileFilter(ArrayList<String> p) {syncFileFilter = p;}
    public void setDirFilter(ArrayList<String> p) {syncDirFilter = p;}

    public String getSyncOptionRetryCount() {return syncOptionRetryCount;}
    public void setSyncOptionRetryCount(String p) {syncOptionRetryCount = p;}

    public boolean isSyncOptionSyncEmptyDirectory() {return syncOptionSyncEmptyDir;}
    public void setSyncOptionSyncEmptyDirectory(boolean p) {syncOptionSyncEmptyDir = p;}

    public boolean isSyncOptionSyncHiddenFile() {return syncOptionSyncHiddenFile;}
    public void setSyncOptionSyncHiddenFile(boolean p) {syncOptionSyncHiddenFile = p;}

    public boolean isSyncOptionSyncHiddenDirectory() {return syncOptionSyncHiddenDir;}
    public void setSyncOptionSyncHiddenDirectory(boolean p) {syncOptionSyncHiddenDir = p;}

    public boolean isSyncOptionSyncSubDirectory() {return syncOptionSyncSubDir;}
    public void setSyncOptionSyncSubDirectory(boolean p) {syncOptionSyncSubDir = p;}

    public boolean isSyncOptionUseSmallIoBuffer() {return syncOptionUseSmallIoBuffer;}
    public void setSyncOptionUseSmallIoBuffer(boolean p) {syncOptionUseSmallIoBuffer = p;}

    public boolean isSyncTestMode() {return syncOptionSyncTestMode;}
    public void setSyncTestMode(boolean p) {syncOptionSyncTestMode = p;}

    public boolean isSyncOptionDifferentFileBySize() {return syncOptionDeterminChangedFileBySize;}
    public void setSyncOptionDifferentFileBySize(boolean p) {syncOptionDeterminChangedFileBySize = p;}

    public boolean isSyncOptionDifferentFileByTime() {return syncOptionDeterminChangedFileByTime;}
    public void setSyncOptionDifferentFileByTime(boolean p) {syncOptionDeterminChangedFileByTime = p;}

    public int getSyncOptionDifferentFileAllowableTime() {return syncOptionDeterminChangedFileByTimeValue;}
    public void setSyncOptionDifferentFileAllowableTime(int p) {syncOptionDeterminChangedFileByTimeValue = p;}

//    public boolean isSyncUseFileCopyByTempNamex() {return syncOptionUseFileCopyByTempName;}
//    public void setSyncUseFileCopyByTempNamex(boolean p) {syncOptionUseFileCopyByTempName = p;}
    public boolean isSyncOptionUseExtendedDirectoryFilter1() {return syncOptionUseExtendedDirectoryFilter1;}
    public void setSyncOptionUseExtendedDirectoryFilter1(boolean p) {syncOptionUseExtendedDirectoryFilter1 = p;}

    public String getSyncOptionWifiStatusOption() {return syncOptionWifiStatus;}
    public void setSyncOptionWifiStatusOption(String p) {syncOptionWifiStatus = p;}

    private ArrayList<String> syncOptionWifiConnectedAccessPointWhiteList = new ArrayList<String>();
    public ArrayList<String> getSyncOptionWifiConnectedAccessPointWhiteList() {return syncOptionWifiConnectedAccessPointWhiteList;}
    public void setSyncOptionWifiConnectedAccessPointWhiteList(ArrayList<String> p) {syncOptionWifiConnectedAccessPointWhiteList = p;}

    private ArrayList<String> syncOptionWifiConnectedAddressWhiteList = new ArrayList<String>();
    public ArrayList<String> getSyncOptionWifiConnectedAddressWhiteList() {return syncOptionWifiConnectedAddressWhiteList;}
    public void setSyncOptionWifiConnectedAddressWhiteList(ArrayList<String> p) {syncOptionWifiConnectedAddressWhiteList = p;}

    public boolean isSyncOptionTaskSkipIfConnectAnotherWifiSsid() {return syncTaskSkipIfConnectAnotherWifiSsid;}
    public void setSyncOptionTaskSkipIfConnectAnotherWifiSsid(boolean skip) {syncTaskSkipIfConnectAnotherWifiSsid = skip;}

    public void setSyncOptionSyncWhenCharging(boolean charging) {syncOptionSyncOnlyCharging = charging;}
    public boolean isSyncOptionSyncWhenCharging() {return syncOptionSyncOnlyCharging;}

    public void setSyncOptionDeleteFirstWhenMirror(boolean first) {syncOptionDeleteFirstWhenMirror = first;}
    public boolean isSyncOptionDeleteFirstWhenMirror() {return syncOptionDeleteFirstWhenMirror;}

    private boolean syncOptionConfirmNotExistsExifDate = true;
    public void setSyncOptionConfirmNotExistsExifDate(boolean enabled) {syncOptionConfirmNotExistsExifDate=enabled;}
    public boolean isSyncOptionConfirmNotExistsExifDate() {return syncOptionConfirmNotExistsExifDate;}

    private boolean syncOptionNeverOverwriteTargetFileIfItIsNewerThanTheMasterFile = false;
    public void setSyncOptionNeverOverwriteTargetFileIfItIsNewerThanTheMasterFile(boolean enabled) {syncOptionNeverOverwriteTargetFileIfItIsNewerThanTheMasterFile=enabled;}
    public boolean isSyncOptionNeverOverwriteTargetFileIfItIsNewerThanTheMasterFile() {return syncOptionNeverOverwriteTargetFileIfItIsNewerThanTheMasterFile;}

    private boolean syncOptionIgnoreDirectoriesOrFilesThatContainUnusableCharacters = false;
    public void setSyncOptionIgnoreDirectoriesOrFilesThatContainUnusableCharacters(boolean enabled) {syncOptionIgnoreDirectoriesOrFilesThatContainUnusableCharacters=enabled;}
    public boolean isSyncOptionIgnoreDirectoriesOrFilesThatContainUnusableCharacters() {return syncOptionIgnoreDirectoriesOrFilesThatContainUnusableCharacters;}

    public boolean isSyncOptionDoNotUseRenameWhenSmbFileWrite() {return syncOptionDoNotUseRenameWhenSmbFileWrite;}
    public void setSyncOptionDoNotUseRenameWhenSmbFileWrite(boolean p) {syncOptionDoNotUseRenameWhenSmbFileWrite = p;}

    public void setLastSyncTime(String p) {syncLastSyncTime = p;}
    public void setLastSyncResult(int p) {syncLastSyncResult = p;}

    public String getLastSyncTime() {return syncLastSyncTime;}
    public int getLastSyncResult() {return syncLastSyncResult;}

    public void setSyncTaskRunning(boolean p) {syncTaskIsRunning = p;}
    public boolean isSyncTaskRunning() {return syncTaskIsRunning;}

    public int getSyncTaskPosition() {return syncTaskPosition;}
    public void setSyncTaskPosition(int p) {syncTaskPosition = p;}

    @Override
    public SyncTaskItem clone() {
        SyncTaskItem npfli = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            oos.flush();
            oos.close();

            baos.flush();
            byte[] ba_buff = baos.toByteArray();
            baos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(ba_buff);
            ObjectInputStream ois = new ObjectInputStream(bais);

            npfli = (SyncTaskItem) ois.readObject();
            ois.close();
            bais.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return npfli;
    }

    public boolean isSame(SyncTaskItem sti) {
        boolean result = false;
        if ((syncTasｋName.equals(sti.getSyncTaskName()) &&
                (syncTasｋGroup.equals(sti.getSyncTaskGroup())) &&
                (syncTaskEnabled==sti.isSyncTaskAuto()) &&
                (syncOptionSyncTestMode==sti.isSyncTestMode()) &&
                (syncTaskType.equals(sti.getSyncTaskType())) &&
                (syncTwoWayConflictOption.equals(sti.getSyncTwoWayConflictFileRule())) &&
                (syncTwoWayConflictKeepConflictFile==sti.isSyncTwoWayKeepConflictFile() ))&&
                (syncTaskMasterFolderType.equals(sti.getMasterFolderType())) &&
                (syncTaskMasterFolderDirName.equals(sti.getMasterDirectoryName())) &&
                (syncTaskMasterLocalMountPoint.equals(sti.getMasterLocalMountPoint())) &&
                (syncTaskMasterFolderRemoteSmbShareName.equals(sti.getMasterRemoteSmbShareName())) &&
                (syncTaskMasterFolderRemoteIpAddress.equals(sti.getMasterSmbAddr())) &&
                (syncTaskMasterFolderRemoteHostName.equals(sti.getMasterSmbHostName())) &&
                (syncTaskMasterFolderRemotePortNumber.equals(sti.getMasterSmbPort())) &&
                (syncTaskMasterFolderRemoteUserName.equals(sti.getMasterSmbUserName())) &&
                (syncTaskMasterFolderRemotePassword.equals(sti.getMasterSmbPassword())) &&
                (syncTaskMasterFolderRemoteDomain.equals(sti.getMasterSmbDomain())) &&
                (syncTaskMasterFolderSmbProtocol.equals(sti.getMasterSmbProtocol())) &&
                (syncTaskMasterFolderSmbIpcSigningEnforced==sti.isMasterSmbIpcSigningEnforced()) &&
                (syncTaskMasterFolderRemovableStorageID.equals(sti.getMasterRemovableStorageID()))) {
//                Log.v("","step1");
            if ((syncTaskTargetFolderType.equals(sti.getTargetFolderType())) &&
                    (syncTaskTargetFolderDirName.equals(sti.getTargetDirectoryName())) &&
                    (syncTaskTargetLocalMountPoint.equals(sti.getTargetLocalMountPoint())) &&
                    (syncTaskTargetFolderRemoteSmbShareName.equals(sti.getTargetSmbShareName())) &&
                    (syncTaskTargetFolderRemoteIpAddress.equals(sti.getTargetSmbAddr())) &&
                    (syncTaskTargetFolderRemoteHostName.equals(sti.getTargetSmbHostName())) &&
                    (syncTaskTargetFolderRemotePortNumber.equals(sti.getTargetSmbPort())) &&
                    (syncTaskTargetFolderRemoteUserName.equals(sti.getTargetSmbUserName())) &&
                    (syncTaskTargetFolderRemotePassword.equals(sti.getTargetSmbPassword())) &&
                    (syncTaskTargetFolderRemoteDomain.equals(sti.getTargetSmbDomain())) &&
                    (syncTaskTargetFolderSmbProtocol.equals(sti.getTargetSmbProtocol())) &&
                    (syncTaskTargetFolderSmbIpcSigningEnforced==sti.isTargetSmbIpcSigningEnforced()) &&
                    (syncTaskTargetFolderRemovableStorageID.equals(sti.getTargetRemovableStorageID()))) {
//                Log.v("","step2");
                if ((syncTaskTargetZipFileName.equals(sti.getTargetZipOutputFileName())) &&
                        (syncTaskTargetZipCompOptionCompLevel.equals(sti.getTargetZipCompressionLevel())) &&
                        (syncTaskTargetZipCompOptionCompMethod.equals(sti.getTargetZipCompressionMethod())) &&
                        (syncTaskTargetZipCompOptionEncrypt.equals(sti.getTargetZipEncryptMethod())) &&
                        (syncTaskTargetZipCompOptionPassword.equals(sti.getTargetZipPassword())) &&
                        (syncTaskTargetZipCompOptionEncoding.equals(sti.getTargetZipFileNameEncoding())) &&
                        (syncTaskTargetZipUseSdcard ==sti.isTargetZipUseExternalSdcard()) &&
                        (syncTaskTargetZipUseUsb ==sti.isTargetZipUseUsb()) &&
                        (syncTaskArchiveRenameWhenArchive ==sti.isArchiveUseRename()) &&
                        (syncTaskArchiveRenameFileTemplate.equals(sti.getArchiveRenameFileTemplate())) &&
                        (syncTaskArchiveRetentionPeriod ==sti.getArchiveRetentionPeriod()) &&
                        (syncTaskArchiveCreateDirectory ==sti.isArchiveCreateDirectory()) &&
                        (syncTaskArchiveSaveDirectoryTemplate.equals(sti.getArchiveCreateDirectoryTemplate())) &&
                        (syncTaskArchiveSuffixDigit.equals(sti.getArchiveSuffixOption())) &&
                        (syncTaskArchiveEnable ==sti.isArchiveEnabled()) &&
                        (syncFileTypeAudio==sti.isSyncFileTypeAudio()) &&
                        (syncFileTypeImage==sti.isSyncFileTypeImage()) &&
                        (syncFileTypeVideo==sti.isSyncFileTypeVideo()) &&

                        (syncOptionRootDirFileToBeProcessed==sti.isSyncProcessRootDirFile()) &&
                        (syncOptionProcessOverrideCopyMove==sti.isSyncOverrideCopyMoveFile()) &&
                        (syncOptionConfirmOverrideDelete==sti.isSyncConfirmOverrideOrDelete()) &&
                        (syncOptionForceLastModifiedUseSmbsync==sti.isSyncDetectLastModifiedBySmbsync()) &&
                        (syncOptionNotUsedLastModifiedForRemote==sti.isSyncDoNotResetFileLastModified()) &&
                        (syncOptionDeterminChangedFileSizeGreaterThanTargetFile==sti.isSyncDifferentFileSizeGreaterThanTagetFile()) &&
                        (syncOptionRetryCount.equals(sti.getSyncOptionRetryCount())) &&
                        (syncOptionSyncEmptyDir==sti.isSyncOptionSyncEmptyDirectory()) &&
                        (syncTaskTargetUseTakenDateTimeForDirectoryNameKeyword == sti.isTargetUseTakenDateTimeToDirectoryNameKeyword()) &&
                        (syncOptionSyncHiddenFile==sti.isSyncOptionSyncHiddenFile()) &&
                        (syncOptionSyncHiddenDir==sti.isSyncOptionSyncHiddenDirectory()) &&
                        (syncOptionSyncSubDir==sti.isSyncOptionSyncSubDirectory()) &&
                        (syncOptionUseSmallIoBuffer==sti.isSyncOptionUseSmallIoBuffer()) &&
                        (syncOptionDeterminChangedFileBySize==sti.isSyncOptionDifferentFileBySize()) &&
                        (syncOptionDeterminChangedFileByTime==sti.isSyncOptionDifferentFileByTime()) &&
                        (syncOptionDeterminChangedFileByTimeValue == sti.getSyncOptionDifferentFileAllowableTime()) &&
//                        (syncOptionUseFileCopyByTempName==sti.isSyncUseFileCopyByTempName()) &&
                        (syncOptionDeleteFirstWhenMirror==sti.isSyncOptionDeleteFirstWhenMirror()) &&
                        (syncOptionConfirmNotExistsExifDate==sti.isSyncOptionConfirmNotExistsExifDate()) &&

                        (syncOptionUseExtendedDirectoryFilter1==sti.isSyncOptionUseExtendedDirectoryFilter1()) &&

                        (syncOptionNeverOverwriteTargetFileIfItIsNewerThanTheMasterFile==sti.isSyncOptionNeverOverwriteTargetFileIfItIsNewerThanTheMasterFile()) &&

                        (syncOptionIgnoreDirectoriesOrFilesThatContainUnusableCharacters==sti.isSyncOptionIgnoreDirectoriesOrFilesThatContainUnusableCharacters()) &&

                        (syncOptionDoNotUseRenameWhenSmbFileWrite==sti.isSyncOptionDoNotUseRenameWhenSmbFileWrite()) &&

                        (syncOptionWifiStatus.equals(sti.getSyncOptionWifiStatusOption())) &&

                        (syncTaskSkipIfConnectAnotherWifiSsid==sti.isSyncOptionTaskSkipIfConnectAnotherWifiSsid()) &&
                        (syncOptionSyncOnlyCharging==sti.isSyncOptionSyncWhenCharging())) {

                    String ff_cmp1 = "";
                    for (String item : syncFileFilter) ff_cmp1 += item;

                    String ff_cmp2 = "";
                    for (String item : sti.getFileFilter()) ff_cmp2 += item;

                    String df_cmp1 = "";
                    for (String item : syncDirFilter) df_cmp1 += item;

                    String df_cmp2 = "";
                    for (String item : sti.getDirFilter()) df_cmp2 += item;

                    String wap_cmp1 = "";
                    for (String item : syncOptionWifiConnectedAccessPointWhiteList) wap_cmp1 += item;

                    String wap_cmp2 = "";
                    for (String item : sti.getSyncOptionWifiConnectedAccessPointWhiteList()) wap_cmp2 += item;

                    String wad_cmp1 = "";
                    for (String item : syncOptionWifiConnectedAddressWhiteList) wad_cmp1 += item;

                    String wad_cmp2 = "";
                    for (String item : sti.getSyncOptionWifiConnectedAddressWhiteList()) wad_cmp2 += item;

                    if ((ff_cmp1.equals(ff_cmp2)) &&
                            (df_cmp1.equals(df_cmp2)) &&
                            (wap_cmp1.equals(wap_cmp2)) &&
                            (wad_cmp1.equals(wad_cmp2))
                            ) {
                        result = true;
                    }
                }
            }


        }
        return result;
    }
}
