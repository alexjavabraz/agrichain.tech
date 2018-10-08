package br.com.bjbraz.smartcontract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class Tracking extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a031916331781556002805460a060020a60ff021916905561186290819061004390396000f3006080604052600436106100985763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d2ee91b811461009d578063455312ad146100c85780634f0d49d5146100df57806350ffdf7c146100f2578063535975e21461011457806380151fa314610129578063ab03085c1461013e578063f48ed22d1461015e578063fac2977514610180575b600080fd5b3480156100a957600080fd5b506100b26101a2565b6040516100bf919061164f565b60405180910390f35b3480156100d457600080fd5b506100dd61063e565b005b6100dd6100ed3660046114d1565b6106f2565b3480156100fe57600080fd5b506101076107a0565b6040516100bf9190611753565b34801561012057600080fd5b506100dd6107a7565b34801561013557600080fd5b506101076108b4565b34801561014a57600080fd5b506101076101593660046113a6565b6108ba565b34801561016a57600080fd5b5061017361105e565b6040516100bf9190611722565b34801561018c57600080fd5b506101956110c6565b6040516100bf9190611660565b6000546060908190600160a060020a031633146101dd5760405160e560020a62461bcd0281526004016101d490611733565b60405180910390fd5b604080516008808252610120820190925290816020015b60608152602001906001900390816101f45750506012805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152939450908301828280156102905780601f1061026557610100808354040283529160200191610290565b820191906000526020600020905b81548152906001019060200180831161027357829003601f168201915b50505050508160008151811015156102a457fe5b602090810290910101526013546102ba906110cf565b8160018151811015156102c957fe5b60209081029091018101919091526014805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529283018282801561035b5780601f106103305761010080835404028352916020019161035b565b820191906000526020600020905b81548152906001019060200180831161033e57829003601f168201915b505050505081600281518110151561036f57fe5b60209081029091018101919091526015805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156104015780601f106103d657610100808354040283529160200191610401565b820191906000526020600020905b8154815290600101906020018083116103e457829003601f168201915b505050505081600381518110151561041557fe5b6020908102909101015260165461042b906110cf565b81600481518110151561043a57fe5b60209081029091018101919091526017805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156104cc5780601f106104a1576101008083540402835291602001916104cc565b820191906000526020600020905b8154815290600101906020018083116104af57829003601f168201915b50505050508160058151811015156104e057fe5b60209081029091018101919091526018805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156105725780601f1061054757610100808354040283529160200191610572565b820191906000526020600020905b81548152906001019060200180831161055557829003601f168201915b505050505081600681518110151561058657fe5b60209081029091018101919091526019805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156106185780601f106105ed57610100808354040283529160200191610618565b820191906000526020600020905b8154815290600101906020018083116105fb57829003601f168201915b505050505081600781518110151561062c57fe5b602090810290910101529050805b5090565b600054600160a060020a0316331461066b5760405160e560020a62461bcd0281526004016101d490611733565b6002805474ff000000000000000000000000000000000000000019167402000000000000000000000000000000000000000017908190556040517f9146df6dc3725d43aefb4c714955239b068466fd5c10ae1abf2ec3453316839e916106dc9160a060020a90910460ff1690611674565b60405180910390a1600054600160a060020a0316ff5b600054600160a060020a0316331461071f5760405160e560020a62461bcd0281526004016101d490611733565b66038d7ea4c6800034101561073357600080fd5b6003849055600683905560018054600160a060020a0380851673ffffffffffffffffffffffffffffffffffffffff1992831617835560028054918516919092161780825574ff0000000000000000000000000000000000000000191660a060020a83021790555050505050565b6004545b90565b60008054600160a060020a031633146107d55760405160e560020a62461bcd0281526004016101d490611733565b60055460ff161580156107eb575060085460ff16155b1561083757506001546002805474ff0000000000000000000000000000000000000000191674030000000000000000000000000000000000000000179055600160a060020a031661087a565b506000546002805474ff0000000000000000000000000000000000000000191674040000000000000000000000000000000000000000179055600160a060020a03165b604051600160a060020a03821690303180156108fc02916000818181858888f193505050501580156108b0573d6000803e3d6000fd5b5050565b60095490565b60008054819081908190600160a060020a031633146108ee5760405160e560020a62461bcd0281526004016101d490611733565b6002805460a060020a900460ff16600581111561090757fe5b14156109285760405160e560020a62461bcd0281526004016101d490611743565b600360025460a060020a900460ff16600581111561094257fe5b14156109635760405160e560020a62461bcd0281526004016101d490611743565b600460025460a060020a900460ff16600581111561097d57fe5b141561099e5760405160e560020a62461bcd0281526004016101d490611743565b6109a78b6111f6565b6003548810159250600654881115915060045488116109c8576004546109ca565b875b60045560075488106109de576007546109e0565b875b60075560408051610100810182528c8152426020808301919091529181018c9052606081018b9052608081018a905260a0810189905260c0810188905260e08101879052600980546001810180835560009290925282518051929460089092027f6e1540171b6c0c960b71a7020d9f60077f6af931a8bbf590da0223dacf75c7af0192610a70928492019061122a565b50602082810151600183015560408301518051610a93926002850192019061122a565b5060608201518051610aaf91600384019160209091019061122a565b506080820151600482015560a08201518051610ad591600584019160209091019061122a565b5060c08201518051610af191600684019160209091019061122a565b5060e08201518051610b0d91600784019160209091019061122a565b5050509050828015610b22575060055460ff16155b15610d88576005805460ff19168415151790556009805482908110610b4357fe5b9060005260206000209060080201600a60008201816000019080546001816001161561010002031660029004610b7a9291906112a4565b506001820154816001015560028201816002019080546001816001161561010002031660029004610bac9291906112a4565b5060038201816003019080546001816001161561010002031660029004610bd49291906112a4565b506004820154816004015560058201816005019080546001816001161561010002031660029004610c069291906112a4565b5060068201816006019080546001816001161561010002031660029004610c2e9291906112a4565b5060078201816007019080546001816001161561010002031660029004610c569291906112a4565b5050600c805460408051602060026000196001861615610100020190941693909304601f81018490048402820184019092528181528594503393610cf39391929091830182828015610ce95780601f10610cbe57610100808354040283529160200191610ce9565b820191906000526020600020905b815481529060010190602001808311610ccc57829003601f168201915b5050505050611206565b600a8054604080516020601f60026101006001871615026000190190951694909404938401819004810282018101909252828152610d559390929091830182828015610ce95780601f10610cbe57610100808354040283529160200191610ce9565b604080517f4e6577206d61782076616c756520726561636865640000000000000000000000815290519081900360150190a45b818015610d98575060085460ff16155b15610fc9576008805460ff19168315151790556009805482908110610db957fe5b9060005260206000209060080201601260008201816000019080546001816001161561010002031660029004610df09291906112a4565b506001820154816001015560028201816002019080546001816001161561010002031660029004610e229291906112a4565b5060038201816003019080546001816001161561010002031660029004610e4a9291906112a4565b506004820154816004015560058201816005019080546001816001161561010002031660029004610e7c9291906112a4565b5060068201816006019080546001816001161561010002031660029004610ea49291906112a4565b5060078201816007019080546001816001161561010002031660029004610ecc9291906112a4565b5050600c805460408051602060026000196001861615610100020190941693909304601f81018490048402820184019092528181528594503393610f349391929091830182828015610ce95780601f10610cbe57610100808354040283529160200191610ce9565b600a8054604080516020601f60026101006001871615026000190190951694909404938401819004810282018101909252828152610f969390929091830182828015610ce95780601f10610cbe57610100808354040283529160200191610ce9565b604080517f4e6577206d696e2076616c756520726561636865640000000000000000000000815290519081900360150190a45b6002805474ff000000000000000000000000000000000000000019167405000000000000000000000000000000000000000017908190556040517fd4f2c5d5177870d3df6e34f8c11a95092f97d4afb3269f3b83c9c98830785f84916110489160a060020a90910460ff16908e908e908e908e908e908e908e90611682565b60405180910390a19a9950505050505050505050565b600054606090600160a060020a0316331461108e5760405160e560020a62461bcd0281526004016101d490611733565b5060408051808201909152600181527f3100000000000000000000000000000000000000000000000000000000000000602082015290565b60055460ff1690565b606060008082818515156111185760408051808201909152600181527f3000000000000000000000000000000000000000000000000000000000000000602082015294506111ed565b8593505b831561113357600190920191600a8404935061111c565b826040519080825280601f01601f191660200182016040528015611161578160200160208202803883390190505b5091505060001982015b85156111e95781516000198201917f01000000000000000000000000000000000000000000000000000000000000006030600a8a0601029184919081106111ae57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600a8604955061116b565b8194505b50505050919050565b805181906000106108b057600080fd5b80516000908290151561121c5760009150611224565b602083015191505b50919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061126b57805160ff1916838001178555611298565b82800160010185558215611298579182015b8281111561129857825182559160200191906001019061127d565b5061063a929150611319565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106112dd5780548555611298565b8280016001018555821561129857600052602060002091601f016020900482015b828111156112985782548255916001019190600101906112fe565b6107a491905b8082111561063a576000815560010161131f565b600061133f82356117cb565b9392505050565b6000601f8201831361135757600080fd5b813561136a61136582611788565b611761565b9150808252602083016020830185838301111561138657600080fd5b6113918382846117e2565b50505092915050565b600061133f82356107a4565b600080600080600080600060e0888a0312156113c157600080fd5b873567ffffffffffffffff8111156113d857600080fd5b6113e48a828b01611346565b975050602088013567ffffffffffffffff81111561140157600080fd5b61140d8a828b01611346565b965050604088013567ffffffffffffffff81111561142a57600080fd5b6114368a828b01611346565b95505060606114478a828b0161139a565b945050608088013567ffffffffffffffff81111561146457600080fd5b6114708a828b01611346565b93505060a088013567ffffffffffffffff81111561148d57600080fd5b6114998a828b01611346565b92505060c088013567ffffffffffffffff8111156114b657600080fd5b6114c28a828b01611346565b91505092959891949750929550565b600080600080608085870312156114e757600080fd5b60006114f3878761139a565b94505060206115048782880161139a565b935050604061151587828801611333565b925050606061152687828801611333565b91505092959194509250565b600061153d826117b6565b80845260208401935083602082028501611556856117b0565b60005b8481101561158d5783830388526115718383516115b1565b925061157c826117b0565b602098909801979150600101611559565b50909695505050505050565b6115a2816117ba565b82525050565b6115a2816117d7565b60006115bc826117b6565b8084526115d08160208601602086016117ee565b6115d98161181e565b9093016020019392505050565b601581527f596f75277265206e6f7420746865206f776e65722e0000000000000000000000602082015260400190565b600f81527f496e76616c6964205354415455532e0000000000000000000000000000000000602082015260400190565b6115a2816107a4565b6020808252810161133f8184611532565b6020810161166e8284611599565b92915050565b6020810161166e82846115a8565b6101008101611691828b6115a8565b81810360208301526116a3818a6115b1565b905081810360408301526116b781896115b1565b905081810360608301526116cb81886115b1565b90506116da6080830187611646565b81810360a08301526116ec81866115b1565b905081810360c083015261170081856115b1565b905081810360e083015261171481846115b1565b9a9950505050505050505050565b6020808252810161133f81846115b1565b6020808252810161166e816115e6565b6020808252810161166e81611616565b6020810161166e8284611646565b60405181810167ffffffffffffffff8111828210171561178057600080fd5b604052919050565b600067ffffffffffffffff82111561179f57600080fd5b506020601f91909101601f19160190565b60200190565b5190565b151590565b60006006821061063a57fe5b600160a060020a031690565b600061166e826117bf565b82818337506000910152565b60005b838110156118095781810151838201526020016117f1565b83811115611818576000848401525b50505050565b601f01601f1916905600a265627a7a723058201336810f686261b8b82d04723cdac763330901f6f525aef34dd55abaa5eb3a6d6c6578706572696d656e74616cf50037";

    public static final String FUNC_GETMINTRACKED = "getMinTracked";

    public static final String FUNC_FINALIZEPOLICY = "finalizePolicy";

    public static final String FUNC_SETUP = "setup";

    public static final String FUNC_GETMAXTEMPERATURERECEIVED = "getMaxTemperatureReceived";

    public static final String FUNC_VERIFYANDFINALIZE = "verifyAndFinalize";

    public static final String FUNC_LISTTOTALTRACKS = "listTotalTracks";

    public static final String FUNC_ADDTRACK = "addTrack";

    public static final String FUNC_GETMAXTRACKED = "getMaxTracked";

    public static final String FUNC_GETMAXTEMPERATUREREACHED = "getMaxTemperatureReached";

    public static final Event FINISHEDTRACKANDPAYED_EVENT = new Event("FinishedTrackAndPayed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}), new ArrayList<>());
    ;

    public static final Event NEWTRACK_EVENT = new Event("NewTrack", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, 
            		new TypeReference<Utf8String>() {}, 
            		new TypeReference<Utf8String>() {}, 
            		new TypeReference<Utf8String>() {}, 
            		new TypeReference<Uint256>() {}, 
            		new TypeReference<Utf8String>() {}, 
            		new TypeReference<Utf8String>() {}, 
            		new TypeReference<Utf8String>() {})
            		,new ArrayList<>());
    ;

    public static final Event CHANGESTATUS_EVENT = new Event("ChangeStatus", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}), new ArrayList<>());
    ;

    protected Tracking(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Tracking(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<List> getMinTracked() {
        final Function function = new Function(FUNC_GETMINTRACKED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> finalizePolicy() {
        final Function function = new Function(
                FUNC_FINALIZEPOLICY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setup(BigInteger _maxTempAccepted, BigInteger _minTemperatureAccepted, String _sender, String _receiver, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SETUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_maxTempAccepted), 
                new org.web3j.abi.datatypes.generated.Uint256(_minTemperatureAccepted), 
                new org.web3j.abi.datatypes.Address(_sender), 
                new org.web3j.abi.datatypes.Address(_receiver)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> getMaxTemperatureReceived() {
        final Function function = new Function(FUNC_GETMAXTEMPERATURERECEIVED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> verifyAndFinalize() {
        final Function function = new Function(
                FUNC_VERIFYANDFINALIZE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> listTotalTracks() {
        final Function function = new Function(FUNC_LISTTOTALTRACKS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addTrack(String _idDeviceTrack, String _date, String _time, BigInteger _temp, String _humidity, String _latitude, String _longitute) {
        final Function function = new Function(
                FUNC_ADDTRACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_idDeviceTrack), 
                new org.web3j.abi.datatypes.Utf8String(_date), 
                new org.web3j.abi.datatypes.Utf8String(_time), 
                new org.web3j.abi.datatypes.generated.Uint256(_temp), 
                new org.web3j.abi.datatypes.Utf8String(_humidity), 
                new org.web3j.abi.datatypes.Utf8String(_latitude), 
                new org.web3j.abi.datatypes.Utf8String(_longitute)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getMaxTracked() {
        final Function function = new Function(FUNC_GETMAXTRACKED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> getMaxTemperatureReached() {
        final Function function = new Function(FUNC_GETMAXTEMPERATUREREACHED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<Tracking> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Tracking.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Tracking> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Tracking.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<FinishedTrackAndPayedEventResponse> getFinishedTrackAndPayedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FINISHEDTRACKANDPAYED_EVENT, transactionReceipt);
        ArrayList<FinishedTrackAndPayedEventResponse> responses = new ArrayList<FinishedTrackAndPayedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FinishedTrackAndPayedEventResponse typedResponse = new FinishedTrackAndPayedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.eventValue = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<FinishedTrackAndPayedEventResponse> finishedTrackAndPayedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, FinishedTrackAndPayedEventResponse>() {
            @Override
            public FinishedTrackAndPayedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FINISHEDTRACKANDPAYED_EVENT, log);
                FinishedTrackAndPayedEventResponse typedResponse = new FinishedTrackAndPayedEventResponse();
                typedResponse.log = log;
                typedResponse._address = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.eventValue = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<FinishedTrackAndPayedEventResponse> finishedTrackAndPayedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FINISHEDTRACKANDPAYED_EVENT));
        return finishedTrackAndPayedEventObservable(filter);
    }

    public List<NewTrackEventResponse> getNewTrackEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWTRACK_EVENT, transactionReceipt);
        ArrayList<NewTrackEventResponse> responses = new ArrayList<NewTrackEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewTrackEventResponse typedResponse = new NewTrackEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._idDeviceTrack = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._date = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._time = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._temp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._humidity = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse._latitude = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse._longitute = (String) eventValues.getNonIndexedValues().get(7).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewTrackEventResponse> newTrackEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewTrackEventResponse>() {
            @Override
            public NewTrackEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWTRACK_EVENT, log);
                NewTrackEventResponse typedResponse = new NewTrackEventResponse();
                typedResponse.log = log;
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._idDeviceTrack = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._date = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._time = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._temp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse._humidity = (String) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse._latitude = (String) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse._longitute = (String) eventValues.getNonIndexedValues().get(7).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewTrackEventResponse> newTrackEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWTRACK_EVENT));
        return newTrackEventObservable(filter);
    }

    public List<ChangeStatusEventResponse> getChangeStatusEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGESTATUS_EVENT, transactionReceipt);
        ArrayList<ChangeStatusEventResponse> responses = new ArrayList<ChangeStatusEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChangeStatusEventResponse typedResponse = new ChangeStatusEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ChangeStatusEventResponse> changeStatusEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ChangeStatusEventResponse>() {
            @Override
            public ChangeStatusEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANGESTATUS_EVENT, log);
                ChangeStatusEventResponse typedResponse = new ChangeStatusEventResponse();
                typedResponse.log = log;
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ChangeStatusEventResponse> changeStatusEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANGESTATUS_EVENT));
        return changeStatusEventObservable(filter);
    }

    public static Tracking load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Tracking(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Tracking load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Tracking(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class FinishedTrackAndPayedEventResponse {
        public Log log;

        public String _address;

        public BigInteger eventValue;

        public BigInteger status;
    }

    public static class NewTrackEventResponse {
        public Log log;

        public BigInteger status;

        public String _idDeviceTrack;

        public String _date;

        public String _time;

        public BigInteger _temp;

        public String _humidity;

        public String _latitude;

        public String _longitute;
    }

    public static class ChangeStatusEventResponse {
        public Log log;

        public BigInteger status;
    }
}
