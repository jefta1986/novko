/*
 *	@desc   	An object that represents a binding between a Signal and a listener function.
 *               Released under the MIT license
 *				http://millermedeiros.github.com/js-signals/
 *
 *	@version	1.0 - 7th March 2013
 *
 *	@author 	Richard Davey, TypeScript conversion
 *	@author		Miller Medeiros, JS Signals
 *	@author		Robert Penner, AS Signals
 *
 *	@url		http://www.kiwijs.org
 *
 */

import { Signal } from './Signal';

export class SignalBinding {

    /**
     * Context on which listener will be executed (object that should represent the `this` variable inside listener
     * function).
     * @memberOf SignalBinding.prototype
     * @name context
     * @type Object|undefined|null
     */
    public context: any;

    /**
     * Listener priority
     * @type Number
     */
    public priority: number;

    /**
     * If binding is active and should be executed.
     * @type boolean
     */
    public active: boolean = true;

    /**
     * Default parameters passed to listener during `Signal.dispatch` and `SignalBinding.execute`. (curried parameters)
     * @type Array|null
     */
    public params: any;

    /**
     * The number of times the handler function has been called.
     * @type Number
     */
    public callCount: number = 0;

    /**
     * Handler function bound to the signal.
     * @type Function
     * @private
     */
    private _listener: any;

    /**
     * If binding should be executed just once.
     * @type boolean
     * @private
     */
    private _isOnce: boolean;

    /**
     * Reference to Signal object that listener is currently bound to.
     * @type Signal
     * @private
     */
    private _signal: Signal;

    /**
     * Listener arguments. Additional arguments to pass to the callback (listener) function. They will be appended
     * after any arguments usually dispatched.
     * @type Array|null
     */
    private _args: any[];

    /**
     * Object that represents a binding between a Signal and a listener function.
     * <br />- <strong>This is an internal constructor and shouldn't be called by regular users.</strong>
     * <br />- inspired by Joa Ebert AS3 SignalBinding and Robert Penner's Slot classes.
     * @author Miller Medeiros
     * @constructor
     * @internal
     * @name SignalBinding
     * @param {Signal} signal Reference to Signal object that listener is currently bound to.
     * @param {Function} listener Handler function bound to the signal.
     * @param {boolean} isOnce If binding should be executed just once.
     * @param {Object} [listenerContext] Context on which listener will be executed (object that should represent the
     *     `this` variable inside listener function).
     * @param {Number} [priority] The priority level of the event listener. (default = 0).
     * @param {any[]} [args=(none)] - Additional arguments to pass to the callback (listener) function. They will be
     *     appended after any arguments usually dispatched.
     */
    constructor(signal: Signal, listener: any, isOnce: boolean, listenerContext: any, priority: number = 0,
                args?: any[]) {

        this._listener = listener;
        this._isOnce = isOnce;
        this.context = listenerContext;
        this._signal = signal;
        this.priority = priority || 0;
        this._args = args;
    }

    /**
     * Call listener passing arbitrary parameters.
     * <p>If binding was added using `Signal.addOnce()` it will be automatically removed from signal dispatch queue,
     * this method is used internally for the signal dispatch.</p>
     * @param {Array} [paramsArr] Array of parameters that should be passed to the listener
     * @return {*} Value returned by the listener.
     */
    public execute(paramsArr?: any[]) {

        let handlerReturn: any;
        let params: any;

        if (this.active && !!this._listener) {
            params = this.params ? this.params.concat(paramsArr) : paramsArr;

            if (this._args) {
                params = params.concat(this._args);
            }

            handlerReturn = this._listener.apply(this.context, params);

            this.callCount++;

            if (this._isOnce) {
                this.detach();
            }
        }

        return handlerReturn;

    }

    /**
     * Detach binding from signal.
     * - alias to: mySignal.remove(myBinding.getListener());
     * @return {Function|null} Handler function bound to the signal or `null` if binding was previously detached.
     */
    public detach() {

        return this.isBound() ? this._signal.remove(this._listener, this.context) : undefined;

    }

    /**
     * @return {Boolean} `true` if binding is still bound to the signal and have a listener.
     */
    public isBound(): boolean {

        return (!!this._signal && !!this._listener);

    }

    /**
     * @return {boolean} If SignalBinding will only be executed once.
     */
    public isOnce(): boolean {

        return this._isOnce;

    }

    /**
     * @return {Function} Handler function bound to the signal.
     */
    public getListener() {

        return this._listener;

    }

    /**
     * @return {Signal} Signal that listener is currently bound to.
     */
    public getSignal() {

        return this._signal;

    }

    /**
     * Delete instance properties
     * @private
     */
    public _destroy() {

        delete this._signal;
        delete this._listener;
        delete this.context;

    }

    /**
     * @return {string} String representation of the object.
     */
    public toString(): string {

        return '[SignalBinding isOnce:' + this._isOnce + ', isBound:' + this.isBound() + ', active:' + this.active + ']';

    }

}
